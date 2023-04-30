package com.modagbul.BE.domain.user.service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.CheckNicknameResponse;
import com.modagbul.BE.domain.user.dto.UserDto.LoginResponse;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.ConnException;
import com.modagbul.BE.domain.user.exception.NotHaveEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.modagbul.BE.domain.user.constant.UserConstant.Process.LOGIN_SUCCESS;
import static com.modagbul.BE.domain.user.constant.UserConstant.Process.SIGN_UP_ING;
import static com.modagbul.BE.domain.user.constant.UserConstant.Role.ROLE_USER;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final String LOGIN_URL = "https://kapi.kakao.com/v2/user/me";
    private final String DELETE_URL = "https://kapi.kakao.com/v1/user/unlink";
    private final String KAKAO_ACOUNT = "kakao_account";
    private final String VALID_NICKNAME="가능한 닉네임입니다";
    private final String EXISTED_NCIKNAME="이미 존재하는 닉네임입니다";

    @Override
    public LoginResponse login(UserDto.LoginRequest loginRequest) {
        //1. 프론트에게 받은 액세스 토큰 이용해서 사용자 정보 가져오기
        String token = loginRequest.getToken();
        JsonObject userInfo = connectKakao(LOGIN_URL, token);
        String email = getEmail(userInfo);
        String pictureUrl = getPictureUrl(userInfo);
        String gender = getGender(userInfo);
        String age_range = getAgeRange(userInfo);
        User user = saveUser(email, pictureUrl, gender, age_range);
        boolean isSignedUp =  user.getNickName() != null;

        //2. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = createOAuth2UserByJson(authorities, userInfo, email);
        OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
        auth.setDetails(userDetails);

        //3. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, isSignedUp);
        String message = isSignedUp ? LOGIN_SUCCESS.getMessage() : SIGN_UP_ING.getMessage();
        SecurityContextHolder.getContext().setAuthentication(auth);
        return LoginResponse.from(tokenInfoResponse, message);
    }

    @Override
    public LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest) {
        //추가 정보 입력 시
        //1. 프론트엔드에게 받은 (자체) 액세스 토큰 이용해서 사용자 이메일 가져오기
        Authentication authentication = tokenProvider.getAuthentication(additionInfoRequest.getAccessToken());
        User user = userRepository.findByEmail(authentication.getName()).get();
        //2. 추가 정보 저장
        user.setUser(additionInfoRequest.getNickName(), additionInfoRequest.getAddress());
        userRepository.save(user);
        //3. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user, additionInfoRequest);
        OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
        auth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);
        //4. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true);
        return LoginResponse.from(tokenInfoResponse, LOGIN_SUCCESS.getMessage());
    }

    @Override
    public void deleteAccount(UserDto.LoginRequest loginRequest) {
        String token = loginRequest.getToken();
        JsonObject response = connectKakao(DELETE_URL, token);
        User user = SecurityUtils.getLoggedInUser();
        user.setDeleted();
        userRepository.save(user);
    }

    @Override
    public CheckNicknameResponse checkNickname(String nickName) {
        if(this.userRepository.findByNickName(nickName.trim()).isPresent()){
            return new CheckNicknameResponse(EXISTED_NCIKNAME);
        }else{
            return new CheckNicknameResponse(VALID_NICKNAME);
        }
    }

    @Override
    public User validateEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(NotHaveEmailException::new);
    }

    private OAuth2User createOAuth2UserByUser(List<GrantedAuthority> authorities, User user, UserDto.AdditionInfoRequest additionInfoRequest) {
        Map userMap = new HashMap<String, String>();
        userMap.put("email", user.getEmail());
        userMap.put("pictureUrl", user.getImageUrl());
        userMap.put("nickName", user.getNickName());
        userMap.put("address", user.getAddress());
        OAuth2User userDetails = new DefaultOAuth2User(authorities, userMap, "email");
        return userDetails;
    }

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
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT).get("has_email").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT).get("email").getAsString();
        }
        throw new NotHaveEmailException();
    }

    private String getPictureUrl(JsonObject userInfo) {
        return userInfo.getAsJsonObject("properties").get("profile_image").getAsString();
    }

    private String getGender(JsonObject userInfo) {
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT).get("has_gender").getAsBoolean() &&
                !userInfo.getAsJsonObject(KAKAO_ACOUNT).get("gender_needs_agreement").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT).get("gender").getAsString();
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
        if (!userRepository.findByEmail(email).isPresent()) {
            return userRepository.save(user);
        }
        return userRepository.findByEmail(email).get();
    }

}
