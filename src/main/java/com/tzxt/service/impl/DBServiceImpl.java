package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.google.common.collect.Maps;
import com.tzxt.dto.LedgerData;
import com.tzxt.dto.LedgerDataSet;
import com.tzxt.dto.QueryParam;
import com.tzxt.mapper.DDLMapper;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DBService;
import com.tzxt.util.Response;
import com.tzxt.util.SQLUtil;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshang on 17/5/11.
 */
@Service
public class DBServiceImpl implements DBService {

    private Logger logger = LoggerFactory.getLogger(DBServiceImpl.class);

    private final DDLMapper ddlMapper;

    public DBServiceImpl(DDLMapper ddlMapper) {
        this.ddlMapper = ddlMapper;
    }


    /**
     * 创建 表
     *
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    @Override
    public Response<Boolean> createTable(Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        try {

            String sql = SQLUtil.createLedgerDictionaryTable(ledger, ledgerDictionaries);
            logger.info("\n" + sql);
            ddlMapper.executeSQL(sql);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("dynamic create table failed. ledger:{}, dictionaries:{}, cause:{}", ledger, ledgerDictionaries, Throwables.getStackTraceAsString(e));
            return Response.fail("动态创建表失败");
        }
    }

    /**
     * 检测 表是否存在
     *
     * @param name
     * @return
     */
    @Override
    public Response<Boolean> exist(String name) {
        try {
            List<String> tables = ddlMapper.showTables();
//            logger.info(tables.toString());
            if (tables.contains(name)) {
                return Response.ok(Boolean.TRUE);
            } else
                return Response.ok(Boolean.FALSE);
        } catch (Exception e) {
            logger.error("check table exist failed. name:{}, cause:{}", name, Throwables.getStackTraceAsString(e));
            return Response.fail("检测表是否存在失败");
        }
    }

    /**
     * 分页 查询 台账 数据表
     *
     * @param pageNo
     * @param pageSize
     * @param queryParam 查询参数
     * @return
     */
    @Override
    public Response<List<LedgerDataSet>> pageLedgerData(Integer pageNo, Integer pageSize, QueryParam queryParam, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        try {
            Map<String, Object> param = Maps.newHashMap();
            param.put("unit_id", queryParam.getUnitId());
            param.put("mouth", queryParam.getMouth());
            String sql = SQLUtil.selectPageByParam(pageNo, pageSize, param, ledger, ledgerDictionaries);
            logger.info("\n" + sql);
            List<Map> lds = ddlMapper.selectList(sql);
            return Response.ok(assemblyLedgerDataSet(lds, ledgerDictionaries));
        } catch (Exception e) {
            logger.error("page Ledger data failed. pageNo:{}, pageSize:{}, queryParam:{}, cause:{}", pageNo, pageSize, queryParam, Throwables.getStackTraceAsString(e));
            return Response.fail("分页查询台账表数据失败");
        }
    }

    private List<LedgerDataSet> assemblyLedgerDataSet(List<Map> lds, List<LedgerDictionary> ledgerDictionaries) {
        List<LedgerDataSet> ledgerDataSets = Lists.newArrayList();

        lds.forEach(map -> {
            List<LedgerData> ledgerData = Lists.newArrayList();
            ledgerDictionaries.forEach(ledgerDictionary -> {
                LedgerData l = new LedgerData();
//                l.setName(ledgerDictionary.getFieldName());
                l.setValue(map.get(ledgerDictionary.getFieldName()));
                ledgerData.add(l);
            });
            LedgerData l2 = new LedgerData();
//            l2.setName("create_at");
            l2.setValue(map.get("create_at"));
            ledgerData.add(l2);
            LedgerData l3 = new LedgerData();
//            l3.setName("update_at");
            l3.setValue(map.get("update_at"));
            ledgerData.add(l3);
            LedgerDataSet ledgerDataSet = new LedgerDataSet();
            ledgerDataSet.setLedgerData(ledgerData);
            ledgerDataSet.setId(map.get("id"));
            ledgerDataSets.add(ledgerDataSet);
        });

        return ledgerDataSets;
    }
}
