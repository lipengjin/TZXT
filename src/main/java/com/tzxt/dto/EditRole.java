package com.tzxt.dto;

import lombok.Data;

import java.util.List;

/**
 * 编辑 角色
 *
 * Created by wangshang on 17/5/26.
 */
@Data
public class EditRole {
    private Long roleId;

    private List<Long> removedRoleAuthIds;
}
