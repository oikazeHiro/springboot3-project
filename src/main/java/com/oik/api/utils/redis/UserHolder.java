package com.oik.api.utils.redis;


import com.oik.api.entity.User;
import com.oik.api.utils.dto.UserDTO;

public class UserHolder {
    private static final ThreadLocal<User> tl = new ThreadLocal<>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }
}
