package com.tzxt.service;

import com.tzxt.model.RoleAuths;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleAuthService {

    Response<Boolean> insertAll(List<RoleAuths> roleAuths);

    Response<List<RoleAuths>> selectByRoleId(Long roleId);

    Response<Boolean> deleteByRoleId(Long roleId);
}
