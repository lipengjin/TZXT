package com.tzxt.dto;

import lombok.Data;

/**
 * 登录用户
 *
 * Created by wangshang on 17/5/8.
 */
@Data
public class LoginUser {

    private Integer accountType;

    private String userName;

    private String password;

}
