package com.tzxt.dto;

import com.tzxt.model.Role;
import com.tzxt.model.RoleAuths;
import com.tzxt.model.Unit;
import com.tzxt.model.UserAuth;
import lombok.Data;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
@Data
public class UnitAndRoleAndAuthDto {

    private List<Role> roles;

    private List<Unit> units;

    private List<UserAuth> userAuths;

}
