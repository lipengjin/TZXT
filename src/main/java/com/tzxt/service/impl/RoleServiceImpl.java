package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.tzxt.mapper.RoleMapper;
import com.tzxt.model.Role;
import com.tzxt.service.RoleService;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
@Service
public class RoleServiceImpl implements RoleService {
    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }


    @Override
    public Response<Role> findById(Long id) {
        return Response.ok(roleMapper.selectByPrimaryKey(id));
    }

    @Override
    public Response<List<Role>> getAll() {
        return Response.ok(roleMapper.selectAll());
    }

    @Override
    public Response<Integer> insert(Role role) {
        try {
            return Response.ok(roleMapper.insert(role));
        } catch (Exception e) {
            logger.error("insert role failed. role:{}, cause:{}", role, Throwables.getStackTraceAsString(e));
            return Response.fail("保存角色失败");
        }
    }

    @Override
    public Response<Boolean> deleteById(Long roleId) {
        try {
            roleMapper.deleteByPrimaryKey(roleId);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("delete role by id. roleId:{}, cause:{}", roleId, Throwables.getStackTraceAsString(e));
            return Response.fail("");
        }
    }
}
