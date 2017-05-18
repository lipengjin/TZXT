package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 原始数据表 列表
 *
 * Created by wangshang on 17/5/19.
 */
@Data
public class SourceTable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private Date updateAt;

    /**
     * 原始数据表 名称
     */
    private String name;

    /**
     * 原始数据表的注释
     */
    private String comment;
}
