package com.tzxt.service;

import com.tzxt.model.Role;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by dell pc on 2017/5/23.
 */
public interface RoleService {
    public Response<Role> findById(Long id);

    public Response<List<Role>> getAll();

    public Response<Integer> insert(Role role);
}
