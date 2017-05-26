package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.tzxt.mapper.RoleAuthMapper;
import com.tzxt.model.RoleAuths;
import com.tzxt.service.RoleAuthService;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
@Service
public class RoleAuthServiceImpl implements RoleAuthService {

    private Logger logger = LoggerFactory.getLogger(RoleAuthServiceImpl.class);

    private final RoleAuthMapper roleAuthMapper;

    public RoleAuthServiceImpl(RoleAuthMapper roleAuthMapper) {
        this.roleAuthMapper = roleAuthMapper;
    }


    @Override
    public Response<Boolean> insertAll(List<RoleAuths> roleAuths) {
        try {

            roleAuthMapper.insertList(roleAuths);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("insert all role auths failed. roleAuths:{}, cause:{}", roleAuths, Throwables.getStackTraceAsString(e));
            return Response.fail("保存角色权限列表失败");
        }
    }

    @Override
    public Response<List<RoleAuths>> selectByRoleId(Long roleId) {
        try {
            return Response.ok(roleAuthMapper.selectByRoleId(roleId));
        } catch (Exception e) {
            logger.error("select by role id failed. roleId:{}, cause:{}", roleId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据角色id查询权限失败");
        }
    }

    @Override
    public Response<Boolean> deleteByRoleId(Long roleId) {
        try {
            roleAuthMapper.deleteByRoleId(roleId);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("delete auths by role id failed. roleId:{}, cause:{}", roleId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据角色Id删除角色权限失败");
        }
    }
}
