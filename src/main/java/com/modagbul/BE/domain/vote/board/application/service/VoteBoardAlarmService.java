package com.modagbul.BE.domain.vote.board.application.service;

import com.modagbul.BE.domain.team.application.service.TeamValidateService;
import com.modagbul.BE.domain.team.domain.entity.Team;
import com.modagbul.BE.domain.team_member.application.service.TeamMemberInfoService;
import com.modagbul.BE.domain.user.entity.User;
import com.modagbul.BE.domain.user.exception.NotFoundEmailException;
import com.modagbul.BE.domain.user.repository.UserRepository;
import com.modagbul.BE.domain.vote.board.domain.entity.Vote;
import com.modagbul.BE.global.config.fcm.dto.FcmDto;
import com.modagbul.BE.global.config.fcm.service.FcmService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

import static com.modagbul.BE.global.config.fcm.constant.FcmConstant.NewUploadTitle.UPLOAD_VOTE_NEW_TITLE;

@Service
@Transactional
@RequiredArgsConstructor
public class VoteBoardAlarmService {

    private final TeamValidateService teamValidateService;
    private final UserRepository userRepository;
    private final TeamMemberInfoService teamMemberInfoService;
    private final FcmService fcmService;

    public void sendNewUploadVoteAlarm(Vote vote, Long teamId, Long userId) {
        Team team = teamValidateService.validateTeam(teamId);
        User loggedInUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundEmailException());
        if (loggedInUser.isNewUploadPush()) {
            String title = team.getName() + " " + UPLOAD_VOTE_NEW_TITLE.getTitle();
            String message = vote.getTitle();
            Optional<List<String>> fcmTokens = teamMemberInfoService.getTeamMemberFcmToken(teamId, userId);
            if(fcmTokens.isPresent() && !fcmTokens.get().isEmpty()) {
                FcmDto.ToMultiRequest toMultiRequest = new FcmDto.ToMultiRequest(fcmTokens.get(), title, message);
                fcmService.sendMultipleDevices(toMultiRequest);
            }
        }
    }
}
