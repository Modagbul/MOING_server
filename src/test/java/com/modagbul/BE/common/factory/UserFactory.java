package com.modagbul.BE.common.factory;

import com.modagbul.BE.domain.user.constant.UserConstant;
import com.modagbul.BE.domain.user.entity.User;

import java.util.List;

public class UserFactory {
    public static User beforeSignUpUser(){
        User user = new User("1000L","imageUrl","female","20-29", UserConstant.Role.ROLE_USER);
        return  user;
    }

    public static User afterSignUpUser(){
        User user = new User("1000L","imageUrl","female","20-29", UserConstant.Role.ROLE_USER);
        user.setUser("밍수","위례동");
        return user;
    }

}
