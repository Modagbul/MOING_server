package com.modagbul.BE.global.config.redis.service;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.user.service.auth.AuthenticationServiceImpl;
import com.modagbul.BE.global.config.jwt.TokenProvider;
import com.modagbul.BE.global.config.redis.dto.RefreshTokenDto;
import com.modagbul.BE.global.config.redis.exception.NotFoundRefreshToken;
import com.modagbul.BE.global.config.redis.repository.RefreshTokenRepository;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;
    private final UserRepository userRepository;
    private final AuthenticationServiceImpl authenticationService;

    @Override
    public RefreshTokenDto.RefreshTokenResponse refreshToken(RefreshTokenDto.RefreshTokenRequest refreshTokenRequest) {
        //1. refreshToken 검증
        refreshTokenRepository.findById(refreshTokenRequest.getUserId()).orElseThrow(() -> new NotFoundRefreshToken());
        //2. 새로운 accessToken 재발급
        //2.1 시큐리티 설정
        User user = userRepository.findById(refreshTokenRequest.getUserId()).orElseThrow(() -> new NotFoundEmailException());
        List<GrantedAuthority> authorities = authenticationService.initAuthorities();
        OAuth2User userDetails = authenticationService.createOAuth2UserByUser(authorities, user);
        OAuth2AuthenticationToken auth = authenticationService.configureAuthentication(userDetails, authorities);
        //2.2 JWT 토큰 생성
        TokenInfoResponse tokenInfoResponse = tokenProvider.createToken(auth, true, user.getUserId());
        return RefreshTokenDto.RefreshTokenResponse.from(tokenInfoResponse);
    }
}
