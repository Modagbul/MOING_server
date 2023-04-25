package com.modagbul.BE.global.config.security.util;

import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.ConnException;
import com.modagbul.BE.global.config.security.exception.LoggedInUserNotFoundException;
import com.modagbul.BE.global.config.security.exception.NoAuthenticationFoundException;
import com.modagbul.BE.global.config.security.exception.RequiredLoggedInException;
import com.modagbul.BE.global.config.security.exception.UserNotAuthenticatedWithOAuth2Exception;
import com.modagbul.BE.global.config.security.service.CustomUserDetails;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

    public static User getLoggedInUser() {
        try {
            return ((CustomUserDetails) Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication()).getPrincipal()).getUser();
        } catch (NullPointerException e) {
            throw new RequiredLoggedInException();
        }
    }

}