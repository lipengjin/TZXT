package com.tzxt.service;

import com.tzxt.model.Ledger;
import com.tzxt.util.Response;

import java.util.List;

/**
 * Created by wangshang on 17/5/8.
 */
public interface LedgerService {

    /**
     * 分页查询 台账 列表
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    Response<List<Ledger>> pageLedger(Integer pageNo, Integer pageSize, String name);

    /**
     * 根据 id 查询 台账
     *
     * @param ledgerId
     * @return
     */
    Response<Ledger> getById(Long ledgerId);

    /**
     * 创建一个 台账
     *
     * @param ledger
     * @return
     */
    Response<Ledger> create(Ledger ledger);

    /**
     * 更新 台账
     *
     * @param ledger
     * @return
     */
    Response<Boolean> update(Ledger ledger);

    /**
     * 查询 台账 列表
     *
     * @return
     */
    Response<List<Ledger>> selectAll();
}
