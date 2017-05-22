package com.tzxt.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.base.Throwables;
import com.tzxt.mapper.LedgerMapper;
import com.tzxt.model.Ledger;
import com.tzxt.service.LedgerService;
import com.tzxt.util.RandomStringUtil;
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

    /**
     * 根据 id 查询 台账
     *
     * @param ledgerId
     * @return
     */
    @Override
    public Response<Ledger> getById(Long ledgerId) {
        try {
            return Response.ok(ledgerMapper.selectByPrimaryKey(ledgerId));
        } catch (Exception e) {
            logger.error("get ledger by id failed. id:{}, cause:{}", ledgerId, Throwables.getStackTraceAsString(e));
            return Response.fail("根据ID获取台账失败");
        }
    }

    /**
     * 创建一个 台账
     *
     * @param ledger
     * @return
     */
    @Override
    public Response<Ledger> create(Ledger ledger) {
        try {
            // 设置 数据库 表名
            ledger.setTableName(RandomStringUtil.getRandomString());
            ledgerMapper.insertSelective(ledger);
            return Response.ok(ledger);
        } catch (Exception e) {
            logger.error("create ledger failed. ledger:{}, cause:{}", ledger, Throwables.getStackTraceAsString(e));
            return Response.fail("创建台账失败");
        }
    }

    /**
     * 更新 台账
     *
     * @param ledger
     * @return
     */
    @Override
    public Response<Boolean> update(Ledger ledger) {
        try {
            ledgerMapper.updateByPrimaryKeySelective(ledger);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("update ledger failed. ledger:{}, cause:{}", ledger, Throwables.getStackTraceAsString(e));
            return Response.fail("更新台账失败");
        }
    }

    /**
     * 查询 台账 列表
     *
     * @return
     */
    @Override
    public Response<List<Ledger>> selectAll() {
        try {
            return Response.ok(ledgerMapper.selectAll());
        } catch (Exception e) {
            logger.error("select all ledger failed. cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("查询台账列表失败");
        }
    }

    /**
     * 删除 台账
     *
     * @param ledgerId
     * @return
     */
    @Override
    public Response<Boolean> deleteLedger(Long ledgerId) {
        try {
            ledgerMapper.deleteByPrimaryKey(ledgerId);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("delete ledger failed. ledgerId:{}, cause:{}", ledgerId, Throwables.getStackTraceAsString(e));
            return Response.fail("删除台账失败");
        }
    }
}
