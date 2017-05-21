package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.tzxt.mapper.LedgerDictionaryMapper;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.LedgerDictionaryService;
import com.tzxt.service.LedgerService;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangshang on 17/5/10.
 */
@Service
public class LedgerDictionaryServiceImpl implements LedgerDictionaryService {

    private Logger logger = LoggerFactory.getLogger(LedgerDictionaryServiceImpl.class);

    private final LedgerDictionaryMapper ledgerDictionaryMapper;

    public LedgerDictionaryServiceImpl(LedgerDictionaryMapper ledgerDictionaryMapper) {
        this.ledgerDictionaryMapper = ledgerDictionaryMapper;
    }

    /**
     * 根据 台账ID 查询 台账的 数据字典
     *
     * @param ledgerId
     * @return
     */
    @Override
    public Response<List<LedgerDictionary>> selectByLedgerId(Long ledgerId) {
        try {
            return Response.ok(ledgerDictionaryMapper.selectByLedgerId(ledgerId));
        } catch (Exception e) {
            logger.error("select ledger dictionary by ledger id failed. ledgerId:{}, cause:{}", ledgerId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据台账ID查询台账的数据字典失败");
        }
    }

    /**
     * 创建 台账 数据字典
     *
     * @param ledgerDictionaries
     * @return
     */
    @Override
    public Response<Boolean> saveAll(List<LedgerDictionary> ledgerDictionaries) {
        try {
            ledgerDictionaries.forEach(ledgerDictionaryMapper::insertSelective);
//            ledgerDictionaryMapper.insertList(ledgerDictionaries);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("create ledger dictionary failed. ledgerDictionaries:{}, cause:{}", ledgerDictionaries, Throwables.getStackTraceAsString(e));
            return Response.fail("创建台账数据字典失败");
        }
    }

    /**
     * 根据ID 查询 台账数据字典
     *
     * @param ledgerDictionaryId
     * @return
     */
    @Override
    public Response<LedgerDictionary> selectById(Long ledgerDictionaryId) {
        try {
            return Response.ok(ledgerDictionaryMapper.selectByPrimaryKey(ledgerDictionaryId));
        } catch (Exception e) {
            logger.error("select ledger dictionary by id failed. id:{}, cause:{}", ledgerDictionaryId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据ID查询台账数据字典失败");
        }
    }

    /**
     * 更新 台账的 一条 字段 相关属性
     *
     * @param ledgerDictionary
     * @return
     */
    @Override
    public Response<Boolean> update(LedgerDictionary ledgerDictionary) {
        try {
            ledgerDictionaryMapper.updateByPrimaryKeySelective(ledgerDictionary);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("update ledger dictionary failed. ledgerDictionary:{}, cause:{}", ledgerDictionary, Throwables.getStackTraceAsString(e));
            return Response.fail("更新台账数据字典失败");
        }
    }

    /**
     * 清空 某一个台账的数据字典
     *
     * @param ledgerId
     * @return
     */
    @Override
    public Response<Boolean> deleteByLedgerId(Long ledgerId) {
        try {
            ledgerDictionaryMapper.deleteByLedgerId(ledgerId);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("delete dictionaries by ledger id failed. ledgerId:{}, cause:{}", ledgerId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据台账ID删除台账数据字典失败");
        }
    }
}
