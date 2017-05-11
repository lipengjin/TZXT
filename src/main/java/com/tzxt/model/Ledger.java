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
     * <p>
     * .........数据库中的 表名 随机生成一个字符串，添加一个 字段用于前端显示，管理员修改表名时，不需要修改表结构。
     */
    private String name;

    /**
     * 台账在 数据库中 的表名，随机生成
     */
    private String tableName;

    /**
     * 原始表名， 不可修改
     */
    private String sourceTable;
    /**
     * 表级别的注释， 可修改
     */
    private String comment;

}
