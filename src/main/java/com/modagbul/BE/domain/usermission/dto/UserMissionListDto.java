package com.modagbul.BE.domain.usermission.dto;

import com.modagbul.BE.domain.usermission.constant.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserMissionListDto {
    private Long userMissionId;

    private String nickname;
    private String profileImg;

    private Status status;
    private String archive;
    private LocalDateTime submitDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserMissionListDto)) return false;
        UserMissionListDto that = (UserMissionListDto) o;
        return Objects.equals(userMissionId, that.userMissionId) && Objects.equals(nickname, that.nickname) && Objects.equals(profileImg, that.profileImg) && status == that.status && Objects.equals(archive, that.archive) && Objects.equals(submitDate, that.submitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userMissionId, nickname, profileImg, status, archive, submitDate);
    }

    @Override
    public String toString() {
        return "UserMissionListDto{" +
                "userMissionId=" + userMissionId +
                ", nickname='" + nickname + '\'' +
                ", profileImg='" + profileImg + '\'' +
                ", status=" + status +
                ", archive='" + archive + '\'' +
                ", submitDate=" + submitDate +
                '}';
    }
}
