package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 台账 数据字段
 * <p>
 * Created by dell pc on 2017/5/7.
 */
@Data
public class LedgerDictionary {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 台账 ID
     */
    private Long ledgerId;

    /**
     * 台账字段 序号， 暂时不可修改
     */
    private Integer ldIndex;

    /**
     * 字段 名称， 可修改
     */
    private String fieldName;

    /**
     * 字段 类型， 不可修改
     */
    private String fieldType;

    /**
     * 字段级别的注释， 可修改
     */
    private String ldComment;

    /**
     * 类型 长度， 不可修改
     */
    private Integer length;

    /**
     * 原始数据表字段，用于映射 台账字段 到 原始表字段
     */
    private String sourceField;

    private Date createAt;
    private Date updateAt;
}
