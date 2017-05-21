package com.tzxt.service;

import com.tzxt.model.LedgerDictionary;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by wangshang on 17/5/10.
 */
public interface LedgerDictionaryService {
    /**
     * 根据 台账ID 查询 台账的 数据字典
     * @param ledgerId
     * @return
     */
    Response<List<LedgerDictionary>> selectByLedgerId(Long ledgerId);

    /**
     * 创建 台账 数据字典
     *
     * @param ledgerDictionaries
     * @return
     */
    Response<Boolean> saveAll(List<LedgerDictionary> ledgerDictionaries);

    /**
     * 根据ID 查询 台账数据字典
     *
     * @param ledgerDictionaryId
     * @return
     */
    Response<LedgerDictionary> selectById(Long ledgerDictionaryId);

    /**
     * 更新 台账的 一条 字段 相关属性
     *
     * @param ledgerDictionary
     * @return
     */
    Response<Boolean> update(LedgerDictionary ledgerDictionary);

    /**
     * 清空 某一个台账的数据字典
     *
     * @param ledgerId
     * @return
     */
    Response<Boolean> deleteByLedgerId(Long ledgerId);
}
