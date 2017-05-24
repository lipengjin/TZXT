package com.tzxt.service.impl;

import com.tzxt.mapper.RoleMapper;
import com.tzxt.mapper.UnitMapper;
import com.tzxt.model.Role;
import com.tzxt.service.RoleService;
import com.tzxt.util.Response;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
@Service
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper){
        this.roleMapper=roleMapper;
    }


    @Override
    public Response<Role> findById(Long id) {
        return Response.ok(roleMapper.selectByPrimaryKey(id));
    }

    @Override
    public Response<List<Role>> getAll() {
        return Response.ok(roleMapper.selectAll());
    }
}
