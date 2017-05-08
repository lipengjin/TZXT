package com.tzxt.util;


import com.tzxt.model.User;

/**
 * Created by wangshang on 17/4/7.
 *
 * 通过 ThreadLocal 记录当前登录的用户
 *
 */
public class CurrentUser {
    private final static ThreadLocal<User> _user = new ThreadLocal<>();

    public static void set(User user) {
        _user.set(user);
    }

    public static User get() {
        return _user.get();
    }

    public static Long userId() {
        return _user.get().getId();
    }

    public static void clear() {
        _user.remove();
    }
}
