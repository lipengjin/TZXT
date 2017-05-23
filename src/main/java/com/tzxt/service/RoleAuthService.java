package com.tzxt.service;

import com.tzxt.model.RoleAuths;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleAuthService {

    public Response<List<RoleAuths>> getAll();

    public Boolean insert(RoleAuths roleAuths);
}
