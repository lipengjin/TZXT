package com.tzxt.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 台账
 * <p>
 * Created by dell pc on 2017/5/7.
 */
@Data
public class Ledger {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date createAt;
    private Date updateAt;

    /**
     * 台账的 名称， 可修改
     */
    private String name;

    /**
     * 原始表名， 不可修改
     */
    private String sourceTable;
    /**
     * 表级别的注释， 可修改
     */
    private String comment;

}
