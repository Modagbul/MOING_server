package com.modagbul.BE.fcm.service;

import com.modagbul.BE.fcm.dto.FcmDto.ToMultiRequest;
import com.modagbul.BE.fcm.dto.FcmDto.ToSingleRequest;

public interface FcmService {
    String sendSingleDevice(ToSingleRequest toSingleRequest);
    String sendMultipleDevices(ToMultiRequest toMultiRequest);

}

