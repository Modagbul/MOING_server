package com.modagbul.BE.domain.user.service.kakao;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.ConnException;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.global.dto.TokenInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.modagbul.BE.domain.user.constant.UserConstant.Process.LOGIN_SUCCESS;
import static com.modagbul.BE.domain.user.constant.UserConstant.Process.SIGN_UP_ING;
import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.KAKAO_ACOUNT;
import static com.modagbul.BE.domain.user.constant.UserConstant.UserServiceMessage.LOGIN_URL;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class KakaoServiceImpl implements KakaoService {
    @Override
    public JsonObject connectKakao(String reqURL, String token) {
        try {
            URL url = new URL(reqURL);
            System.out.println(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Authorization", "Bearer " + token); //전송할 header 작성, access_token전송


            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;

            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            JsonObject json = JsonParser.parseString(response.toString()).getAsJsonObject();
            return json;
        } catch (IOException e) {
            throw new ConnException();
        }
    }

    @Override
    public String getEmail(JsonObject userInfo) {
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("has_email").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("email").getAsString();
        }
        throw new NotFoundEmailException();
    }

    @Override
    public String getPictureUrl(JsonObject userInfo) {
        return userInfo.getAsJsonObject("properties").get("profile_image").getAsString();
    }

    @Override
    public String getGender(JsonObject userInfo) {
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("has_gender").getAsBoolean() &&
                !userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("gender_needs_agreement").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT.getValue()).get("gender").getAsString();
        }
        return "동의안함";
    }

    @Override
    public String getAgeRange(JsonObject userInfo) {
        String KAKAO_ACOUNT = "kakao_account";
        if (userInfo.getAsJsonObject(KAKAO_ACOUNT).get("has_age_range").getAsBoolean() &&
                !userInfo.getAsJsonObject(KAKAO_ACOUNT).get("age_range_needs_agreement").getAsBoolean()) {
            return userInfo.getAsJsonObject(KAKAO_ACOUNT).get("age_range").getAsString();
        }
        return "동의안함";
    }

}
