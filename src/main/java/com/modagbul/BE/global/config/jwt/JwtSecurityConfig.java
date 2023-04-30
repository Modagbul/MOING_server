package com.modagbul.BE.global.config.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Spring Security 구성에서 JWT 인증을 구현하는 데 필요한 필터를 구성
 */
@RequiredArgsConstructor
public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    private final TokenProvider tokenProvider;


    @Override
    public void configure(HttpSecurity http) {
        JwtFilter customFilter = new JwtFilter(tokenProvider);
        ExceptionHandlerFilter exceptionHandlerFilter= new ExceptionHandlerFilter();
        //UsernamePasswordAuthenticationFilter 앞에 필터로 JwtFilter 추가
        http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(exceptionHandlerFilter, JwtFilter.class);
    }
}
