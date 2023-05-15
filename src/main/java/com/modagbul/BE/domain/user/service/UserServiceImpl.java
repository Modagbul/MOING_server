package com.modagbul.BE.domain.user.service;

import com.google.gson.JsonObject;
import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.dto.UserDto.*;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.exception.NotFoundUserException;
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

    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final KakaoAPIConnector kakao;


    @Override
    public LoginResponse login(UserDto.LoginRequest loginRequest) {
        //1. 프론트에게 받은 액세스 토큰 이용해서 사용자 정보 가져오기
        String token = loginRequest.getToken();
        JsonObject userInfo = kakao.connectKakao(LOGIN_URL.getValue(), token);
        User user = saveUser(kakao.getEmail(userInfo), kakao.getPictureUrl(userInfo), kakao.getGender(userInfo), kakao.getAgeRange(userInfo));
        boolean isSignedUp = user.getNickName() != null;

        //2. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByJson(authorities, userInfo, kakao.getEmail(userInfo));
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //3. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, isSignedUp);
        return LoginResponse.from(tokenInfoResponse, isSignedUp ? LOGIN_SUCCESS.getMessage() : SIGN_UP_ING.getMessage());
    }

    @Override
    public LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest) {
        //추가 정보 입력 시
        //1. 프론트엔드에게 받은 (자체) 액세스 토큰 이용해서 사용자 이메일 가져오기
        Authentication authentication = tokenProvider.getAuthentication(additionInfoRequest.getAccessToken());
        User user = validateEmail(authentication.getName());

        //2. 추가 정보 저장
        user.setUser(additionInfoRequest.getNickName(), additionInfoRequest.getAddress(), additionInfoRequest.getFcmToken());
        userRepository.save(user);

        //3. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //4. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true);
        return LoginResponse.from(tokenInfoResponse, LOGIN_SUCCESS.getMessage());
    }

    @Override
    public void deleteAccount(DeleteAccountRequest deleteAccountRequest) {
        String token = deleteAccountRequest.getToken();
        JsonObject response = kakao.connectKakao(DELETE_URL.getValue(), token);
        User user = validateEmail(SecurityUtils.getLoggedInUser().getEmail());
        user.setDeleted(deleteAccountRequest.getReasonToLeave());
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
        user.setUser(testLoginRequest.getNickName(), testLoginRequest.getAddress(), "fcmToken");
        userRepository.save(user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true);
        return LoginResponse.from(tokenInfoResponse, LOGIN_SUCCESS.getMessage());
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

        teamList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));

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

    @Override
    public AlarmDto getAlarmSetting() {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        return new AlarmDto(user.isNewUploadPush(), user.isRemindPush(), user.isFirePush());
    }

    @Override
    public AlarmChangeDto changeNewUploadAlarm(AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setNewUploadPush(alarmChangeDto.getData());
        return new AlarmChangeDto(user.isNewUploadPush());
    }

    @Override
    public AlarmChangeDto changeRemindAlarm(AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setRemindPush(alarmChangeDto.getData());
        return new AlarmChangeDto(user.isRemindPush());
    }

    @Override
    public AlarmChangeDto changeFireAlarm(AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setFirePush(alarmChangeDto.getData());
        return new AlarmChangeDto(user.isFirePush());
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
        userMap.put("pictureUrl", kakao.getPictureUrl(userInfo));
        userMap.put("gender", kakao.getGender(userInfo));
        userMap.put("age_range", kakao.getAgeRange(userInfo));
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

    public User saveUser(String email, String pictureUrl, String gender, String ageRange) {
        User user = new User(email, pictureUrl, gender, ageRange, ROLE_USER);
        if (!userRepository.findNotDeletedByEmail(email).isPresent()) {
            return userRepository.save(user);
        }
        return userRepository.findNotDeletedByEmail(email).get();
    }
}
