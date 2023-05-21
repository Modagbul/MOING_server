package com.modagbul.BE.domain.user.controller.validate;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.service.validate.UserValidationService;
import com.modagbul.BE.global.dto.ResponseDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.CHECK_ADDITIONALINFO_SUCCESS;
import static com.modagbul.BE.domain.user.constant.UserConstant.EUserResponseMessage.CHECK_NICKNAME;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/users")
@Api(tags = "User API")
public class UserValidationController {

    private final UserValidationService validateService;

    @ApiOperation(value="추가 정보 입력 검사", notes="추가 정보 입력 여부를 검사합니다.")
    @GetMapping("/additional-info")
    public ResponseEntity<ResponseDto> checkAdditionalInfo(){
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_ADDITIONALINFO_SUCCESS.getMessage()));
    }

    @ApiOperation(value = "닉네임 중복 검사", notes = "닉네임 중복 검사를 합니다.")
    @GetMapping("/nickname/{nickName}/available")
    public ResponseEntity<ResponseDto<UserDto.CheckNicknameResponse>> checkNickname(@PathVariable String nickName) {
        return ResponseEntity.ok(ResponseDto.create(HttpStatus.OK.value(), CHECK_NICKNAME.getMessage(), this.validateService.checkNickname(nickName)));
    }

}
