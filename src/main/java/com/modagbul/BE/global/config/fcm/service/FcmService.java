package com.modagbul.BE.global.config.fcm.service;

import com.modagbul.BE.global.config.fcm.dto.FcmDto;

public interface FcmService {
    FcmDto.SingleFcmResponse sendSingleDevice(FcmDto.ToSingleRequest toSingleRequest);
    FcmDto.MultiFcmResponse sendMultipleDevices(FcmDto.ToMultiRequest toMultiRequest);
    FcmDto.SingleFcmResponse sendByTopic(FcmDto.TopicRequest topicRequest);
    FcmDto.SingleFcmResponse sendByTopicCustom(FcmDto.TopicCustomRequest topicRequest);

}

