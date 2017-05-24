package com.tzxt.dto;

import lombok.Data;

/**
 * Created by dell pc on 2017/5/23.
 */
@Data
public class UserManageDto {

    private Integer id;
    private String userName;
    private String realName;
    private String unitName;
    private String role;
    private String auth;
    private String locked;


}
