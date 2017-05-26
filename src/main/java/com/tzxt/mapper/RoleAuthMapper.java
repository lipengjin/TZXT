package com.tzxt.mapper;

import com.tzxt.model.RoleAuths;
import com.tzxt.util.MyMapper;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleAuthMapper extends MyMapper<RoleAuths> {
    List<RoleAuths> selectByRoleId(Long roleId);

    void deleteByRoleId(Long roleId);
}
