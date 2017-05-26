package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 角色 权限
 * Created by dell pc on 2017/5/7.
 */
@Data
public class RoleAuths {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private Date updateAt;

    /**
     * 角色ID
     */
    private Long roleId;
    /**
     * 权限类型
     * 0 - 操作权限
     * 1 - 数据权限
     */
    private Integer authType;
    /**
     * authType - 0 :
     * authType - 1 : 台账名
     */
    private String auth;

    /**
     * 如果是 数据权限 此字段表示 台账 ID，否则 为null
     */
    private Long ledgerId;

    enum AuthType{
        OPERATION, DATA
    }

}
