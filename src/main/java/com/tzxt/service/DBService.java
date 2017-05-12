package com.tzxt.service;

import com.tzxt.dto.LedgerDataSet;
import com.tzxt.dto.QueryParam;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.util.Response;

import java.util.List;

/**
 * 操作 数据库 表级别参数 的服务
 * <p>
 * Created by wangshang on 17/5/11.
 */
public interface DBService {

    /**
     * 创建 表
     *
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    Response<Boolean> createTable(Ledger ledger, List<LedgerDictionary> ledgerDictionaries);

    /**
     * 检测 表是否存在
     *
     * @param name
     * @return
     */
    Response<Boolean> exist(String name);

    /**
     * 分页 查询 台账 表数据
     *
     * @param pageNo
     * @param pageSize
     * @param queryParam 查询参数
     * @return
     */
    Response<List<LedgerDataSet>> pageLedgerData(Integer pageNo, Integer pageSize, QueryParam queryParam, Ledger ledger, List<LedgerDictionary> ledgerDictionaries);

    /**
     * 统计 台账表数据量
     *
     * @param queryParam
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    Response<Long> count(QueryParam queryParam, Ledger ledger, List<LedgerDictionary> ledgerDictionaries);

}
