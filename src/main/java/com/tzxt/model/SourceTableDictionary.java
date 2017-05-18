package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 *  原始数据表的数据字典
 *
 * Created by wangshang on 17/5/19.
 */
@Data
public class SourceTableDictionary {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 台账 ID
     */
    private Long sourceTableId;

    /**
     * 字段 名称
     */
    private String fieldName;

    /**
     * 字段 类型
     */
    private String fieldType;

    /**
     * 字段级别的注释
     */
    private String ldComment;


    private Date createAt;
    private Date updateAt;
}
