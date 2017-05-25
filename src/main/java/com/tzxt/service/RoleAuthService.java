package com.tzxt.service;

import com.tzxt.model.RoleAuths;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleAuthService {

    Response<List<RoleAuths>> getAll();

    Boolean insert(RoleAuths roleAuths);
}
