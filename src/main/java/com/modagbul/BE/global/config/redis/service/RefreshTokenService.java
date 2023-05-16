package com.modagbul.BE.global.config.redis.service;

import com.modagbul.BE.global.config.redis.dto.RefreshTokenDto.RefreshTokenRequest;
import com.modagbul.BE.global.config.redis.dto.RefreshTokenDto.RefreshTokenResponse;

public interface RefreshTokenService {
    RefreshTokenResponse refreshToken(RefreshTokenRequest refreshTokenRequest);
}
