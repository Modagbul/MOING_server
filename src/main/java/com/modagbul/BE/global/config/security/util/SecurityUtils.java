package com.modagbul.BE.global.config.security.util;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.global.config.security.exception.RequiredLoggedInException;
import com.modagbul.BE.global.config.security.service.CustomUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

    public static User getLoggedInUser() {
        try {
            return
                    ((CustomUserDetails) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).getUser();
        } catch (NullPointerException e) {
            throw new RequiredLoggedInException();
        }
    }

}