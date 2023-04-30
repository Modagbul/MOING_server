package com.modagbul.BE.domain.user.entity;

import com.modagbul.BE.domain.teammember.entity.TeamMember;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.modagbul.BE.domain.user.constant.UserConstant.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    private String email;

    @Lob
    private String imageUrl;

    private String gender;

    private String ageRange;

    private boolean isDeleted;

    // 추가정보
    private String nickName;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public User(String email, String imageUrl, String gender, String ageRange, Role role){
        this.email=email;
        this.imageUrl=imageUrl;
        this.gender=gender;
        this.ageRange=ageRange;
        this.role=role;
    }

    public void setUser(String nickName, String address){
        this.nickName=nickName;
        this.address=address;
    }

    public void setDeleted(){
        this.email="withdrawal";
        this.isDeleted=true;
    }
}
