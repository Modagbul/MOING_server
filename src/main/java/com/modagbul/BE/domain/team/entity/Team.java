package com.modagbul.BE.domain.team.entity;

import com.modagbul.BE.domain.notice.entity.Notice;
import com.modagbul.BE.domain.team.constant.TeamConstant.Category;
import com.modagbul.BE.domain.teammember.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Team{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long teamId;

    private String name;

    @Lob
    private String info;

    private String promise;

    @Lob
    private String profileImg;

    private Long leaderId;

    private String invitationCode;

    private boolean approvalStatus;

    @Enumerated(EnumType.STRING)
    private Category category;


    private Integer period;

    private Integer personnel;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(mappedBy = "team")
    private List<TeamMember> teamMembers = new ArrayList<>();

    @OneToMany(mappedBy = "team")
    private List<Notice> notices = new ArrayList<>();

    public void createTeam(Category category, String name, Integer personnel, LocalDate startDate, Integer period, String info, String promise, String profileImg, Long leaderId){
        this.category=category;
        this.name=name;
        this.personnel=personnel;
        this.startDate=startDate;
        this.period=period;
        this.endDate=startDate.plusMonths(period);
        this.info=info;
        this.promise=promise;
        this.profileImg=profileImg;
        this.leaderId=leaderId;
    }

    public void setApprovalStatus(){
        this.approvalStatus=true;
    }

    public void setInvitationCode(String invitationCode){
        this.invitationCode=invitationCode;
    }

    public void updateTeam(String name, LocalDate endDate, String profileImg){
        this.name=name;
        this.endDate=endDate;
        this.profileImg=profileImg;
    }
}
