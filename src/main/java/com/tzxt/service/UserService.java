package com.tzxt.service;

import com.tzxt.dto.LoginUser;
import com.tzxt.model.User;
import com.tzxt.util.Response;

import java.util.List;

/**
 * @since 2016-01-31 21:42
 */
public interface UserService {

    /**
     * 验证用户 密码
     * @param loginUser
     * @return
     */
    Response<User> validate(LoginUser loginUser);

    /**
     * 根据用户ID 获取 用户信息
     * @param userId
     * @return
     */
    Response<User> getById(Long userId);

    Response<List<User>> getAll();
}
