package com.modagbul.BE.domain.user.service.auth;

import com.google.gson.JsonObject;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.user.service.kakao.UserKakaoServiceImpl;
import com.modagbul.BE.domain.user.service.validate.UserValidationService;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.config.redis.repository.RefreshTokenRepository;
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
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    private final UserRepository userRepository;
    private final UserKakaoServiceImpl kakaoService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserValidationService validateService;

    @Override
    public UserDto.LoginResponse login(UserDto.LoginRequest loginRequest) {
        //1. 프론트에게 받은 액세스 토큰 이용해서 사용자 정보 가져오기
        String token = loginRequest.getToken();
        JsonObject userInfo = kakaoService.connectKakao(LOGIN_URL.getValue(), token);
        User user = saveUser(kakaoService.getEmail(userInfo), kakaoService.getPictureUrl(userInfo), kakaoService.getGender(userInfo), kakaoService.getAgeRange(userInfo));
        boolean isSignedUp = user.getNickName() != null;

        //2. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByJson(authorities, userInfo, kakaoService.getEmail(userInfo));
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //3. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, isSignedUp, user.getUserId());
        return UserDto.LoginResponse.from(tokenInfoResponse, isSignedUp ? LOGIN_SUCCESS.getMessage() : SIGN_UP_ING.getMessage(), user.getUserId());
    }

    @Override
    public UserDto.LoginResponse signup(UserDto.AdditionInfoRequest additionInfoRequest) {
        //추가 정보 입력 시
        //1. 프론트엔드에게 받은 (자체) 액세스 토큰 이용해서 사용자 이메일 가져오기
        Authentication authentication = tokenProvider.getAuthentication(additionInfoRequest.getAccessToken());
        User user = validateService.validateEmail(authentication.getName());

        //2. 추가 정보 저장
        user.setUser(additionInfoRequest.getNickName(), additionInfoRequest.getAddress(), additionInfoRequest.getFcmToken());
        userRepository.save(user);

        //3. 스프링 시큐리티 처리
        List<GrantedAuthority> authorities = initAuthorities();
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        //4. JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true, user.getUserId());
        return UserDto.LoginResponse.from(tokenInfoResponse, LOGIN_SUCCESS.getMessage(), user.getUserId());
    }

    @Override
    public void deleteAccount(UserDto.DeleteAccountRequest deleteAccountRequest) {
        //1. 카카오 회원탈퇴 처리
        String token = deleteAccountRequest.getToken();
        JsonObject response = kakaoService.connectKakao(DELETE_URL.getValue(), token);
        //2. Redis에서 해당 사용자의 Refresh Token을 삭제
        User user = validateService.validateEmail(SecurityUtils.getLoggedInUser().getEmail());
        refreshTokenRepository.deleteById(user.getUserId());
        //3. DB에서 삭제처리
        user.setDeleted(deleteAccountRequest.getReasonToLeave());
        userRepository.save(user);
    }

    @Override
    public void logout(UserDto.LoginRequest loginRequest) {
        //1. 카카오 로그아웃 처리
        String token = loginRequest.getToken();
        JsonObject response = kakaoService.connectKakao(LOGOUT_URL.getValue(), token);
        //2. Redis에서 해당 사용자의 Refresh Token을 삭제
        User user = validateService.validateEmail(SecurityUtils.getLoggedInUser().getEmail());
        refreshTokenRepository.deleteById(user.getUserId());
    }

    @Override
    public UserDto.LoginResponse testLogin(UserDto.TestLoginRequest testLoginRequest) {
        User user = new User(testLoginRequest.getEmail(), testLoginRequest.getImageUrl(), testLoginRequest.getGender(), testLoginRequest.getAgeRange(), ROLE_USER);
        user.setUser(testLoginRequest.getNickName(), testLoginRequest.getAddress(), "fcmToken");
        userRepository.save(user);

        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = configureAuthentication(userDetails, authorities);

        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true, user.getUserId());
        return UserDto.LoginResponse.from(tokenInfoResponse, LOGIN_SUCCESS.getMessage(), user.getUserId());
    }

    public User saveUser(String email, String pictureUrl, String gender, String ageRange) {
        User user = new User(email, pictureUrl, gender, ageRange, ROLE_USER);
        if (!userRepository.findNotDeletedByEmail(email).isPresent()) {
            return userRepository.save(user);
        }
        return userRepository.findNotDeletedByEmail(email).get();
    }

    /**
     * User -> OAuth2User
     *
     * @param authorities
     * @param user
     * @return OAuth2User
     */


    public OAuth2User createOAuth2UserByUser(List<GrantedAuthority> authorities, User user) {
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
     *
     * @param authorities
     * @param userInfo
     * @param email
     * @return OAuth2User
     */

    private OAuth2User createOAuth2UserByJson(List<GrantedAuthority> authorities, JsonObject userInfo, String email) {
        Map userMap = new HashMap<String, String>();
        userMap.put("email", email);
        userMap.put("pictureUrl", kakaoService.getPictureUrl(userInfo));
        userMap.put("gender", kakaoService.getGender(userInfo));
        userMap.put("age_range", kakaoService.getAgeRange(userInfo));
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        OAuth2User userDetails = new DefaultOAuth2User(authorities, userMap, "email");
        return userDetails;
    }

    public List<GrantedAuthority> initAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(String.valueOf(ROLE_USER)));
        return authorities;
    }

    public OAuth2AuthenticationToken configureAuthentication(OAuth2User userDetails, List<GrantedAuthority> authorities) {
        OAuth2AuthenticationToken auth = new OAuth2AuthenticationToken(userDetails, authorities, "email");
        auth.setDetails(userDetails);
        SecurityContextHolder.getContext().setAuthentication(auth);
        return auth;
    }

}
