package com.modagbul.BE.domain.user.service.kakao;

import com.google.gson.JsonObject;
import com.modagbul.BE.domain.user.dto.UserDto;

public interface KakaoService {
    JsonObject connectKakao(String reqURL, String token);
    String getEmail(JsonObject userInfo);
    String getPictureUrl(JsonObject userInfo);
    String getGender(JsonObject userInfo);
    String getAgeRange(JsonObject userInfo);
}
