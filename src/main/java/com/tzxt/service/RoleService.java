package com.tzxt.service;

import com.tzxt.model.Role;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleService {
    Response<Role> findById(Long id);

    Response<List<Role>> getAll();

    Response<Integer> insert(Role role);

    Response<Boolean> deleteById(Long roleId);
}
