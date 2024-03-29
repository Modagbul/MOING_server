package com.modagbul.BE.global.config.fcm;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import com.modagbul.BE.global.config.fcm.exception.InitializeException;
import com.modagbul.BE.global.config.fcm.exception.MessagingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Configuration
@Slf4j
public class FcmConfig {


    @Value("${firebase.config.path}")
    private String firebaseConfigPath;

    @Value("${firebase.config.projectId}")
    private String projectId;

    @Bean
    public FirebaseApp firebaseApp() {
        try {
            FileInputStream serviceAccount = new FileInputStream(firebaseConfigPath);

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId(projectId)
                    .build();

            return FirebaseApp.initializeApp(options);
        } catch (FileNotFoundException e) {
            throw new IllegalStateException("파일을 찾을 수 없습니다."+e.getMessage());
        } catch (IOException e) {
            throw new InitializeException();
        }
    }

    @Bean
    public FirebaseMessaging firebaseMessaging() {
        try {
            return FirebaseMessaging.getInstance(firebaseApp());
        }catch (IllegalStateException e) {
            throw new MessagingException("FirebaseApp 초기화에 실패하였습니다." + e.getMessage());
        } catch (NullPointerException e) {
            throw new IllegalStateException("FirebaseApp을 불러오는데 실패하였습니다."+e.getMessage());
        } catch (Exception e) {
             throw new IllegalArgumentException("firebaseConfigPath를 읽어오는데 실패하였습니다."+e.getMessage());
        }
    }
}
