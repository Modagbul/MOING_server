package com.modagbul.BE.global.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.modagbul.BE.global.config.jwt.constants.JwtConstants;
import com.modagbul.BE.global.config.jwt.exception.*;
import com.modagbul.BE.global.dto.ErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    private final TokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException, JwtException {
        String jwt = resolveToken(request);
        String requestURI = request.getRequestURI();
        try {
            if (StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
                boolean isAdditionalInfoProvided = tokenProvider.getAdditionalInfoProvided(jwt);
                if (isAdditionalInfoProvided) {
                    Authentication authentication = tokenProvider.getAuthentication(jwt);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                    log.debug("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}", authentication.getName(), requestURI);
                } else {
                    throw new AdditionalInfoException();
                }
            }
        }
        catch(AdditionalInfoException e){
            request.setAttribute("exception", JwtConstants.JWTExceptionList.ADDITIONAL_REQUIRED_TOKEN.getErrorCode());
        } catch (SecurityException | MalformedException e) {
            request.setAttribute("exception", JwtConstants.JWTExceptionList.MAL_FORMED_TOKEN.getErrorCode());
        } catch (ExpiredException e) {
            request.setAttribute("exception", JwtConstants.JWTExceptionList.EXPIRED_TOKEN.getErrorCode());
        } catch (UnsupportedException e) {
            request.setAttribute("exception", JwtConstants.JWTExceptionList.UNSUPPORTED_TOKEN.getErrorCode());
        } catch (IllegalException e) {
            request.setAttribute("exception", JwtConstants.JWTExceptionList.ILLEGAL_TOKEN.getErrorCode());
        } catch (Exception e) {
            log.error("================================================");
            log.error("JwtFilter - doFilterInternal() 오류발생");
            log.error("token : {}", jwt);
            log.error("Exception Message : {}", e.getMessage());
            log.error("Exception StackTrace : {");
            e.printStackTrace();
            log.error("}");
            log.error("================================================");
            request.setAttribute("exception", JwtConstants.JWTExceptionList.UNKNOWN_ERROR.getErrorCode());
        }
        filterChain.doFilter(request, response);
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}