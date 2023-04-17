package com.modagbul.BE.global.config.jwt;

import com.modagbul.BE.global.config.jwt.constants.JwtConstants;
import com.modagbul.BE.global.config.jwt.constants.JwtConstants.JWTExceptionList;
import com.nimbusds.jose.shaded.json.JSONObject;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 인증되지 않은 사용자가 보호된 리소스에 액세스 할 때 호출
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String exception = (String)request.getAttribute("exception");
        if(exception == null) setResponse(response, JWTExceptionList.UNKNOWN_ERROR);
            //잘못된 타입의 토큰인 경우
        else if(exception.equals(JWTExceptionList.ILLEGAL_TOKEN.getErrorCode())) setResponse(response, JWTExceptionList.ILLEGAL_TOKEN);
            //토큰 만료된 경우
        else if(exception.equals(JWTExceptionList.EXPIRED_TOKEN.getErrorCode())) {
            setResponse(response, JWTExceptionList.EXPIRED_TOKEN);
        }
        //지원되지 않는 토큰인 경우
        else if(exception.equals(JWTExceptionList.UNSUPPORTED_TOKEN.getErrorCode())) setResponse(response, JWTExceptionList.UNSUPPORTED_TOKEN);
        else setResponse(response, JWTExceptionList.ACCESS_DENIED);

    }

    private void setResponse(HttpServletResponse response, JWTExceptionList exceptionCode) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        JSONObject responseJson = new JSONObject();
        responseJson.put("message", exceptionCode.getMessage());
        responseJson.put("errorCode", exceptionCode.getErrorCode());

        response.getWriter().print(responseJson);
    }
}