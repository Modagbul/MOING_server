package com.modagbul.BE.domain.usermission.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;

@Getter
@AllArgsConstructor
public enum Status {
    COMPLETE,
    INCOMPLETE,
    PENDING;

}