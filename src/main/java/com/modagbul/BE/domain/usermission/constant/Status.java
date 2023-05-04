package com.modagbul.BE.domain.usermission.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public enum Status {
    COMPLETE("COMPLETE"), INCOMPLETE("INCOMPLETE"), PENDING("PENDING");

    private final String name;
    Status(String name){
        this.name=name;
    }



}