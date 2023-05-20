package com.modagbul.BE.domain.user.service.validate;

import com.modagbul.BE.domain.user.dto.UserDto.CheckNicknameResponse;
import com.modagbul.BE.domain.user.entity.User;

public interface ValidateService {
    CheckNicknameResponse checkNickname(String nickName);
    User validateEmail(String email);
}
