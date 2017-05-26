package com.tzxt.dto;

import lombok.Data;

import java.util.List;

/**
 * 新建 角色 模型
 *
 * Created by wangshang on 17/5/26.
 */
@Data
public class NewRoleModel {

    private Long roleId;

    private String roleName;

    private List<Long> ledgerIds;
}
