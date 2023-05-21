package com.modagbul.BE.domain.user.service.mypage;

import com.modagbul.BE.domain.team_member.entity.TeamMember;
import com.modagbul.BE.domain.user.dto.UserDto;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundUserException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.global.config.security.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserMyPageServiceImpl implements UserMyPageService {
    private final UserRepository userRepository;
    @Override
    public UserDto.MyPageInfoDto getUserInfo() {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        List<UserDto.TeamList> teamList= new ArrayList<>();

        List<TeamMember> teamMembers = user.getTeamMembers();
        for (TeamMember teamMember : teamMembers) {
            teamList.add(new UserDto.TeamList(teamMember.getTeam().getName(), teamMember.getTeam().getProfileImg(),teamMember.getTeam().getEndDate().toString()));
        }

        teamList.sort((o1, o2) -> o2.getDate().compareTo(o1.getDate()));

        return new UserDto.MyPageInfoDto(user.getNickName(), user.getIntroduction(),user.getImageUrl(), teamList.size(),teamList);

    }
    @Override
    public UserDto.MyPageEditDto updateUserInfo(UserDto.MyPageEditDto myPageEditDto) {

        Long userId = SecurityUtils.getLoggedInUser().getUserId();

        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        user.setMypage(myPageEditDto.getNickName(), myPageEditDto.getIntroduction());
        userRepository.save(user);
        return new UserDto.MyPageEditDto(user.getNickName(),user.getIntroduction());

    }

    @Override
    public UserDto.AlarmDto getAlarmSetting() {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);
        return new UserDto.AlarmDto(user.isNewUploadPush(), user.isRemindPush(), user.isFirePush());
    }

    @Override
    public UserDto.AlarmChangeDto changeNewUploadAlarm(UserDto.AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setNewUploadPush(alarmChangeDto.getData());
        return new UserDto.AlarmChangeDto(user.isNewUploadPush());
    }

    @Override
    public UserDto.AlarmChangeDto changeRemindAlarm(UserDto.AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setRemindPush(alarmChangeDto.getData());
        return new UserDto.AlarmChangeDto(user.isRemindPush());
    }

    @Override
    public UserDto.AlarmChangeDto changeFireAlarm(UserDto.AlarmChangeDto alarmChangeDto) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setFirePush(alarmChangeDto.getData());
        return new UserDto.AlarmChangeDto(user.isFirePush());
    }
    @Override
    public String changeProfileImg(String string) {
        Long userId = SecurityUtils.getLoggedInUser().getUserId();
        User user = userRepository.findById(userId).orElseThrow(NotFoundUserException::new);

        user.setImageUrl(string);
        return user.getImageUrl();
    }
}
