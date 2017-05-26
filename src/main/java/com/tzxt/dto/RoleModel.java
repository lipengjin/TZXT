package com.tzxt.dto;

import com.tzxt.model.Role;
import lombok.Data;

/**
 * Created by wangshang on 17/5/26.
 */
@Data
public class RoleModel {

    private Role role;

    private String auths;

}
