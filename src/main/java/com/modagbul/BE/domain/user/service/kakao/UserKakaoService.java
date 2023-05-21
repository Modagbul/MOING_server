package com.modagbul.BE.domain.user.service.kakao;

import com.google.gson.JsonObject;

public interface UserKakaoService {
    JsonObject connectKakao(String reqURL, String token);
    String getEmail(JsonObject userInfo);
    String getPictureUrl(JsonObject userInfo);
    String getGender(JsonObject userInfo);
    String getAgeRange(JsonObject userInfo);
}
