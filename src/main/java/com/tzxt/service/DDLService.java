package com.tzxt.service;

import com.tzxt.model.LedgerDictionary;

import java.util.List;

/**
 * 操作 数据库 表级别参数 的服务
 *
 * Created by wangshang on 17/5/11.
 */
public interface DDLService {

    /**
     * 创建 表
     *
     * @param name
     * @param ledgerDictionaries
     * @return
     */
    Boolean createTable(String name, List<LedgerDictionary> ledgerDictionaries);

    /**
     * 检测 表是否存在
     *
     * @param name
     * @return
     */
    Boolean exist(String name);
}
