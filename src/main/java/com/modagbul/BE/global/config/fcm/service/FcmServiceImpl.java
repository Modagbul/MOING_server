package com.modagbul.BE.global.config.fcm.service;

import com.google.firebase.messaging.*;
import com.modagbul.BE.global.config.fcm.dto.FcmDto.*;
import com.modagbul.BE.global.config.fcm.exception.NotificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class FcmServiceImpl implements FcmService {

    private final FirebaseMessaging firebaseMessaging;

    @Override
    public SingleFcmResponse sendSingleDevice(ToSingleRequest toSingleRequest) {

        Notification notification = Notification.builder()
                .setTitle(toSingleRequest.getTitle())
                .setBody(toSingleRequest.getBody())
                .build();

        Message message = Message.builder()
                .setToken(toSingleRequest.getRegistrationToken())
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setChannelId("FCM Channel")
                                .setTitle(toSingleRequest.getTitle())
                                .setBody(toSingleRequest.getBody())
                                .build())
                        .build())
                .build();

        try {
            String response = firebaseMessaging.send(message);
            return new SingleFcmResponse(response);
        } catch (FirebaseMessagingException e) {
            throw handleException(e);
        }
    }

    @Override
    public MultiFcmResponse sendMultipleDevices(ToMultiRequest toMultiRequest) {

        Notification notification = Notification.builder()
                .setTitle(toMultiRequest.getTitle())
                .setBody(toMultiRequest.getBody())
                .build();

        MulticastMessage message = MulticastMessage.builder()
                .addAllTokens(toMultiRequest.getRegistrationToken())
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setChannelId("FCM Channel")
                                .setTitle(toMultiRequest.getTitle())
                                .setBody(toMultiRequest.getBody())
                                .build())
                        .build())
                .build();

        try {
            BatchResponse response = firebaseMessaging.sendMulticast(message);
            List<String> failedTokens = new ArrayList<>();

            if (response.getFailureCount() > 0) { // 실패한게 한개라도 있다면
                List<SendResponse> responses = response.getResponses();

                for (int i = 0; i < responses.size(); i++) {
                    if (!responses.get(i).isSuccessful()) {
                        //실패한 기기의 등록 토큰을 리스트에 추가
                        failedTokens.add(toMultiRequest.getRegistrationToken().get(i));
                    }
                }
            }

            String messageString = String.format("%d개의 메시지가 성공적으로 전송되었습니다.", response.getSuccessCount());

            return new MultiFcmResponse(messageString, failedTokens);

        } catch (FirebaseMessagingException e) {
            throw handleException(e);
        }
    }

    @Override
    public SingleFcmResponse sendByTopic(TopicRequest topicRequest) {
        Notification notification = Notification.builder()
                .setTitle(topicRequest.getTitle())
                .setBody(topicRequest.getBody())
                .build();


        Message message = Message.builder()
                .setCondition(createCondition(topicRequest))
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setChannelId("FCM Channel")
                                .setTitle(topicRequest.getTitle())
                                .setBody(topicRequest.getBody())
                                .build())
                        .build())
                .build();



        try {
            String response = firebaseMessaging.send(message);
            return new SingleFcmResponse(response);
        } catch (FirebaseMessagingException e) {
            throw handleException(e);
        }
    }


    @Override
    public SingleFcmResponse sendByTopicCustom(TopicCustomRequest topicRequest) {


        Message message = Message.builder()
                .setCondition(createConditionCustom(topicRequest))
                .setAndroidConfig(AndroidConfig.builder()
                        .setPriority(AndroidConfig.Priority.HIGH)
                        .setNotification(AndroidNotification.builder()
                                .setChannelId("FCM Channel")
                                .setTitle(topicRequest.getTitle())
                                .setBody(topicRequest.getBody())
                                .build())
                        .build())
                .build();


        try {
            String response = FirebaseMessaging.getInstance().send(message);
            return new SingleFcmResponse(response);
        } catch (FirebaseMessagingException e) {
            throw handleException(e);
        }
    }

    private static String createCondition(TopicRequest topicRequest) {
        List<String> topics=topicRequest.getTopic();

        //조건 topic이 하나인 경우
        if (topics.size() == 1) {
            log.info("====================");
            log.info(topics.get(0)+" in topics");
            log.info("====================");
            return String.format("'%s' in topics", topics.get(0));
        }

        String operator=topicRequest.getOperator();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < topics.size(); i++) {
            sb.append(String.format("'%s' in topics", topics.get(i)));
            if (i != topics.size() - 1) {
                sb.append(" " + operator + " ");
            }
        }
        log.info("====================");
        log.info(sb.toString());
        log.info("====================");
        return sb.toString();
    }

    private static String createConditionCustom(TopicCustomRequest topicCustomRequest){
        StringBuilder sb = new StringBuilder();
        List<String> conditions=topicCustomRequest.getConditions();
        List<String> operators=topicCustomRequest.getOperator();

        if(conditions.size()-operators.size()!=1){
            log.warn("연산자 개수: "+operators.size()+", 조건절 개수: "+conditions.size());
            throw new NotificationException("연산자의 개수가 적절하지 않습니다! 반드시 조건절보다 한개 적어야 합니다."+" ");
        }

        for(int i=0;i<topicCustomRequest.getConditions().size();i++) {
            String[] conditionsSplit = conditions.get(i).split("(?<=[&|]{2})|(?=[&|]{2})"); // "&&" 또는 "||"를 기준으로 분리
            sb.append(" ( ");
            for (String c : conditionsSplit) {
                if(!c.trim().equals("&&")&&!c.trim().equals("||")) {
                    sb.append("'"+c.trim()+"'").append(" in topics").append(" "); // 각각의 조건부분에 "in topics" 추가
                }else{
                    sb.append(c.trim()).append(" ");
                }
            }
            if(i!=conditions.size()-1) {
                sb.append(") " + operators.get(i));
            }
        }
        sb.append(")");

        log.info("====================");
        log.info(sb.toString().trim());
        log.info("====================");
        return sb.toString().trim();
    }



    private NotificationException handleException(FirebaseMessagingException e) {
        String errorCode = e.getErrorCode().name();
        String errorMessage = e.getMessage();

        switch (errorCode) {
            case "INVALID_ARGUMENT":
                return new NotificationException("올바르지 않은 인자 값입니다: " + errorMessage);
            case "NOT_FOUND":
                return new NotificationException("등록 토큰이 유효하지 않거나, 주제(Topic)가 존재하지 않습니다: " + errorMessage);
            case "UNREGISTERED":
                return new NotificationException("해당 주제(Topic)의 구독이 해지되었습니다: " + errorMessage);
            case "UNAVAILABLE":
                return new NotificationException("서비스를 사용할 수 없습니다: " + errorMessage);
            default:
                return new NotificationException("메시지 전송에 실패했습니다: " + errorMessage);
        }
    }

}


