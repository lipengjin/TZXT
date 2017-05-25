package com.tzxt.model;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 用户的 权限
 *
 * Created by wangshang on 17/5/25.
 */
@Data
@Builder
public class UserAuth {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String auth;
}
