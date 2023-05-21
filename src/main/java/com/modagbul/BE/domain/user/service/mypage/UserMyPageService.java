package com.modagbul.BE.domain.user.service.mypage;

import com.modagbul.BE.domain.user.dto.UserDto.AlarmChangeDto;
import com.modagbul.BE.domain.user.dto.UserDto.AlarmDto;
import com.modagbul.BE.domain.user.dto.UserDto.MyPageEditDto;
import com.modagbul.BE.domain.user.dto.UserDto.MyPageInfoDto;

public interface UserMyPageService {
    MyPageInfoDto getUserInfo();
    MyPageEditDto updateUserInfo(MyPageEditDto myPageEditDto );
    AlarmDto getAlarmSetting();
    AlarmChangeDto changeNewUploadAlarm(AlarmChangeDto alarmChangeDto);
    AlarmChangeDto changeRemindAlarm(AlarmChangeDto alarmChangeDto);
    AlarmChangeDto changeFireAlarm(AlarmChangeDto alarmChangeDto);
    String changeProfileImg(String string);
}
