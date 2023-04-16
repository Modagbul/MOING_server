package com.modagbul.BE.global.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDto<T> {
    private int statusCode;
    private String message;
    private T data;

    public static <T> ResponseDto<T> create(int statusCode, String message) {
        return new ResponseDto<>(statusCode, message, null);
    }

    public static <T> ResponseDto<T> create(int statusCode, String message, T dto) {
        return new ResponseDto<>(statusCode, message, dto);
    }
}
