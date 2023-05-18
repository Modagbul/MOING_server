package com.modagbul.BE.global.config.redis.controller;

import com.modagbul.BE.global.config.redis.dto.RefreshTokenDto;
import com.modagbul.BE.global.config.redis.service.RefreshTokenService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.TOKEN_REFRESH_SUCCESS;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "토큰 재발급 API")
public class RefreshTokenController {

    private final RefreshTokenService refreshTokenService;

    @ApiOperation(value = "토큰 재발급", notes = "토큰을 재발급합니다 합니다.")
    @PostMapping("/auth/refresh")
    public ResponseEntity<ResponseDto<RefreshTokenDto.RefreshTokenResponse>> tokenRefresh(@Valid @RequestBody RefreshTokenDto.RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), TOKEN_REFRESH_SUCCESS.getMessage(), refreshTokenService.refreshToken(refreshTokenRequest)));
    }
}
