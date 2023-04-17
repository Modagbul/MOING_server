package com.modagbul.BE.global.config.security.util;

import com.modagbul.BE.global.config.security.exception.LoggedInUserNotFoundException;
import com.modagbul.BE.global.config.security.exception.NoAuthenticationFoundException;
import com.modagbul.BE.global.config.security.exception.UserNotAuthenticatedWithOAuth2Exception;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Transactional
public class SecurityUtils {

    public static OAuth2User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new NoAuthenticationFoundException();
        }

        if (!(authentication instanceof OAuth2AuthenticationToken)) {
            throw new UserNotAuthenticatedWithOAuth2Exception();
        }

        OAuth2User loggedInUser = ((OAuth2AuthenticationToken) authentication).getPrincipal();

        if (loggedInUser == null) {
            throw new LoggedInUserNotFoundException();
        }

        return loggedInUser;
    }

}