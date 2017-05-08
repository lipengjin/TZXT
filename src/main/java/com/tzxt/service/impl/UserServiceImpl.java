package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.tzxt.dto.LoginUser;
import com.tzxt.mapper.UserMapper;
import com.tzxt.model.User;
import com.tzxt.service.UserService;
import com.tzxt.util.MD5;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by dell pc on 2017/5/7.
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    /**
     * 验证用户 密码
     *
     * @param loginUser
     * @return
     */
    @Override
    public Response<User> validate(LoginUser loginUser) {
        try {
            User user = userMapper.selectByName(loginUser.getUserName());
            if (user == null) {
                return Response.fail("该用户不存在");
            }
            if (!user.getPassword().equals(MD5.encode(loginUser.getPassword()))) {
                return Response.fail("用户密码错误");
            }
            user.setPassword(null);
            return Response.ok(user);
        } catch (Exception e) {
            logger.error("validate user password failed. user:{}, cause:{}", loginUser, Throwables.getStackTraceAsString(e));
            return Response.fail("用户密码验证失败");
        }
    }

    /**
     * 根据用户ID 获取 用户信息
     *
     * @param userId
     * @return
     */
    @Override
    public Response<User> getById(Long userId) {
        try {
            return Response.ok(userMapper.selectByPrimaryKey(userId));
        } catch (Exception e) {
            logger.error("get user by userId failed. userId:{}, cause:{}", userId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据用户ID获取用户信息失败");
        }
    }


}
