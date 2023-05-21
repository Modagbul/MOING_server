package com.modagbul.BE.domain.user.service.validate;

import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.EXISTED_NCIKNAME;
import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.VALID_NICKNAME;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserValidationServiceImpl implements UserValidationService {

    private final UserRepository userRepository;

    @Override
    public UserDto.CheckNicknameResponse checkNickname(String nickName) {
        if (this.userRepository.findNotDeletedByNickName(nickName.trim()).isPresent()) {
            return new UserDto.CheckNicknameResponse(EXISTED_NCIKNAME.getValue());
        } else {
            return new UserDto.CheckNicknameResponse(VALID_NICKNAME.getValue());
        }
    }

    @Override
    public User validateEmail(String email) {
        return this.userRepository.findNotDeletedByEmail(email).orElseThrow(() -> new NotFoundEmailException());
    }
}
