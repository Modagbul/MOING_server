package com.modagbul.BE.fcm.service;

import com.modagbul.BE.fcm.dto.FcmDto.*;

public interface FcmService {
    SingleFcmResponse sendSingleDevice(ToSingleRequest toSingleRequest);
    MultiFcmResponse sendMultipleDevices(ToMultiRequest toMultiRequest);
    SingleFcmResponse sendByTopic(TopicRequest topicRequest);
    SingleFcmResponse sendByTopicCustom(TopicCustomRequest topicRequest);

}

