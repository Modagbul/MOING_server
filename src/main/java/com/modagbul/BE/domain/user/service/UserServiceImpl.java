package com.modagbul.BE.domain.user.service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.*;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.ConnException;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.exception.NotFoundUserException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.modagbul.BE.domain.user.constant.UserConstant.Process.LOGIN_SUCCESS;
import static com.modagbul.BE.domain.user.constant.UserConstant.Process.SIGN_UP_ING;
import static com.modagbul.BE.domain.user.constant.UserConstant.Role.ROLE_USER;
import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.*;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    @Value("${kakao.client_id}")
    private String kakoClietId;

    @Value("${kakao.redirect_uri}")
    private String redirectUri;

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;


    @Override
    public LoginResponse login(UserDto.LoginRequest loginRequest) {
        //1. 프론트에게 받은 액세스 토큰 이용해서 사용자 정보 가져오기
        String token = loginRequest.getToken();
        JsonObject userInfo = connectKakao(LOGIN_URL.getValue(), token);
        User user = saveUser(getEmail(userInfo), getPictureUrl(userInfo), getGender(userInfo), getAgeRange(userInfo));
        boolean isSignedUp = user.getNickName() != null;

        //2. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByJson(authorities, userInfo, getEmail(userInfo));
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //3. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, isSignedUp);
        return LoginResponse.from(tokenInfoResponse, token, isSignedUp ? LOGIN_SUCCESS.getMessage() : SIGN_UP_ING.getMessage());
    }

    @Override
    public LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest) {
        //추가 정보 입력 시
        //1. 프론트엔드에게 받은 (자체) 액세스 토큰 이용해서 사용자 이메일 가져오기
        Authentication authentication = tokenProvider.getAuthentication(additionInfoRequest.getAccessToken());
        User user = validateEmail(authentication.getName());

        //2. 추가 정보 저장
        user.setUser(additionInfoRequest.getNickName(), additionInfoRequest.getAddress());
        userRepository.save(user);

        //3. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //4. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true);
        return LoginResponse.from(tokenInfoResponse, null, LOGIN_SUCCESS.getMessage());
    }

    @Override
    public void deleteAccount(UserDto.LoginRequest loginRequest) {
        String token = loginRequest.getToken();
        JsonObject response = connectKakao(DELETE_URL.getValue(), token);
        User user = validateEmail(SecurityUtils.getLoggedInUser().getEmail());
        user.setDeleted();
        userRepository.save(user);
    }

    @Override
    public CheckNicknameResponse checkNickname(String nickName) {
        if (this.userRepository.findNotDeletedByNickName(nickName.trim()).isPresent()) {
            return new CheckNicknameResponse(EXISTED_NCIKNAME.getValue());
        } else {
            return new CheckNicknameResponse(VALID_NICKNAME.getValue());
        }
    }

    @Override
    public User validateEmail(String email) {
        return this.userRepository.findNotDeletedByEmail(email).orElseThrow(() -> new NotFoundEmailException());
    }

    @Override
    public LoginResponse testLogin(UserDto.TestLoginRequest testLoginRequest) {
        User user = new User(testLoginRequest.getEmail(), testLoginRequest.getImageUrl(), testLoginRequest.getGender(), testLoginRequest.getAgeRange(), ROLE_USER);
        user.setUser(testLoginRequest.getNickName(), testLoginRequest.getAddress());
        userRepository.save(user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true);
        return LoginResponse.from(tokenInfoResponse, null, LOGIN_SUCCESS.getMessage());
    }


    @Override
    public MyPageInfoDto getUserInfo() {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        List<TeamList> teamList= new ArrayList<>();

        List<TeamMember> teamMembers = user.getTeamMembers();
        for (TeamMember teamMember : teamMembers) {
            teamList.add(new TeamList(teamMember.getTeam().getName(), teamMember.getTeam().getProfileImg(),teamMember.getTeam().getEndDate().toString()));
        }

        return new MyPageInfoDto(user.getNickName(), user.getIntroduction(), teamList.size(),teamList);

    }
    @Override
    public MyPageEditDto updateUserInfo(MyPageEditDto myPageEditDto) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        user.setMypage(myPageEditDto.getNickName(), myPageEditDto.getIntroduction());
        userRepository.save(user);
        return new MyPageEditDto(user.getNickName(),user.getIntroduction());

    }

    public String getKakaoAccessToken (String code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id="+kakoClietId); // TODO REST_API_KEY 입력
            sb.append("&redirect_uri="+redirectUri); // TODO 인가코드 받은 redirect_uri 입력
            sb.append("&code=" + code);
            bw.write(sb.toString());
            bw.flush();

            //결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();

            //요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }

            //Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            br.close();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return access_Token;
    }


    /**
     * User -> OAuth2User
     * @param authorities
     * @param user
     * @return OAuth2User
     */

    private OAuth2User createOAuth2UserByUser(List<GrantedAuthority> authorities, User user) {
        Map userMap = new HashMap<String, String>();
        userMap.put("email", user.getEmail());
        userMap.put("pictureUrl", user.getImageUrl());
        userMap.put("nickName", user.getNickName());
        userMap.put("address", user.getAddress());
        OAuth2User userDetails = new DefaultOAuth2User(authorities, userMap, "email");
        return userDetails;
    }

    /**
     * userInfo, email -> OAuth2User
     * @param authorities
     * @param userInfo
     * @param email
     * @return OAuth2User
     */

    private OAuth2User createOAuth2UserByJson(List<GrantedAuthority> authorities, JsonObject userInfo, String email) {
        Map userMap = new HashMap<String, String>();
        userMap.put("email", email);
        userMap.put("pictureUrl", getPictureUrl(userInfo));
        userMap.put("gender", getGender(userInfo));
        userMap.put("age_range", getAgeRange(userInfo));
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = new DefaultOAuth2User(authorities, userMap, "email");
        return userDetails;
    }

    private List<GrantedAuthority> initAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        return authorities;
    }

    private OAuth2AuthenticationToken configureAuthentication(OAuth2User userDetails, List<GrantedAuthority> authorities) {
        OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
        auth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

    private JsonObject connectKakao(String reqURL, String token) {
        try {
            URL url = new URL(reqURL);
            System.out.println(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            return json;
        } catch (IOException e) {
            throw new ConnException();
        }
    }

    private String getEmail(JsonObject userInfo) {
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("has_email").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("email").getAsString();
        }
        throw new NotFoundEmailException();
    }

    private String getPictureUrl(JsonObject userInfo) {
        return userInfo.getAsJsonObject("properties").get("profile_image").getAsString();
    }

    private String getGender(JsonObject userInfo) {
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("has_gender").getAsBoolean() &&
                !userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("gender_needs_agreement").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("gender").getAsString();
        }
        return "동의안함";
    }

    private String getAgeRange(JsonObject userInfo) {
        String KAKAO_ACOUNT = "kakao_account";
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT).get("has_age_range").getAsBoolean() &&
                !userInfo.getAsJsonObject(KAKAO_ACOUNT).get("age_range_needs_agreement").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT).get("age_range").getAsString();
        }
        return "동의안함";
    }

    private User saveUser(String email, String pictureUrl, String gender, String ageRange) {
        User user = new User(email, pictureUrl, gender, ageRange, ROLE_USER);
        if (!userRepository.findNotDeletedByEmail(email).isPresent()) {
            return userRepository.save(user);
        }
        return userRepository.findNotDeletedByEmail(email).get();
    }





}
