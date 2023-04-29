package com.modagbul.BE.domain.user.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import com.modagbul.BE.domain.user.constant.UserConstant.Role;

import javax.persistence.*;

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

    @Column
    private String email;

    @Column
    private String imageUrl;

    @Column
    private String gender;

    @Column
    private String ageRange;

    @Column
    private boolean isDeleted;

    // 추가정보
    @Column
    private String nickName;

    @Column
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
        this.isDeleted=true;
    }
}
