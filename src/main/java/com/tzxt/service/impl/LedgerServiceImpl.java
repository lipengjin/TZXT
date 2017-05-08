package com.tzxt.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Throwables;
import com.tzxt.mapper.LedgerMapper;
import com.tzxt.model.Ledger;
import com.tzxt.service.LedgerService;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshang on 17/5/8.
 */
@Service
public class LedgerServiceImpl implements LedgerService {

    private Logger logger = LoggerFactory.getLogger(LedgerServiceImpl.class);


    private final LedgerMapper ledgerMapper;

    public LedgerServiceImpl(LedgerMapper ledgerMapper) {
        this.ledgerMapper = ledgerMapper;
    }

    /**
     * 分页查询 台账 列表
     *
     * @param pageNo
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public Response<List<Ledger>> pageLedger(Integer pageNo, Integer pageSize, String name) {
        try {
            PageHelper.startPage(pageNo, pageSize);
            return Response.ok(ledgerMapper.selectByParams(name));
        } catch (Exception e) {
            logger.error("page ledgers failed. pageNo:{}, pageSize:{}, name:{}, cause:{}", pageNo, pageSize, name, Throwables.getStackTraceAsString(e));
            return Response.fail("分页查询台账列表失败");
        }
    }
}
