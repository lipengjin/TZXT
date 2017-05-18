package com.tzxt.service;

import com.tzxt.dto.LedgerDataSet;
import com.tzxt.dto.QueryParam;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.util.Response;

import java.util.List;
import java.util.Map;

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

    /**
     * 查询指定 台账的 指定ID 的数据
     *
     * @param ledger
     * @param ledgerDictionaries
     * @param ledgerDataId
     * @return
     */
    Response<LedgerDataSet> selectOne(Ledger ledger, List<LedgerDictionary> ledgerDictionaries, Long ledgerDataId);

    /**
     * 更新 台账 数据
     *
     * @param ledgerId
     * @param ledger
     *@param ledgerDictionaries
     * @param ledgerDataSet  @return
     */
    Response<Boolean> updateLedger(Long ledgerId, Ledger ledger, List<LedgerDictionary> ledgerDictionaries, LedgerDataSet ledgerDataSet);

    /**
     * 查询 符合条件的 所有记录
     *
     * @param unitId
     * @param mouth
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    Response<List<Map<String, Object>>> selectLedgerData(Long unitId, String mouth, Ledger ledger, List<LedgerDictionary> ledgerDictionaries);

    /**
     * 查询 原始表
     *
     * @param sql
     * @return
     */
    Response<List<Map<String, Object>>> selectPage(String sql);

    /**
     *
     * @param ledger
     * @param ledgerDictionaries
     * @param data
     * @return
     */
    Response<Boolean> insertList(Ledger ledger, List<LedgerDictionary> ledgerDictionaries, List<Map<String,Object>> data);
}
