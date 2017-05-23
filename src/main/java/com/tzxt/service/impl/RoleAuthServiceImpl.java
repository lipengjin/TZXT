package com.tzxt.service.impl;

import com.tzxt.mapper.RoleAuthMapper;
import com.tzxt.model.RoleAuths;
import com.tzxt.service.RoleAuthService;
import com.tzxt.util.Response;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {

    private final RoleAuthMapper roleAuthMapper;

    public RoleAuthServiceImpl(RoleAuthMapper roleAuthMapper) {
        this.roleAuthMapper = roleAuthMapper;
    }


    @Override
    public Response<List<RoleAuths>> getAll() {
        return Response.ok(roleAuthMapper.selectAll());
    }

    @Override
    public Boolean insert(RoleAuths roleAuths) {
        return roleAuthMapper.insert(roleAuths)>0;
    }
}
