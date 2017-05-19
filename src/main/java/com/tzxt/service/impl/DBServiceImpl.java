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
            param.put("unit", queryParam.getUnit());
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

    /**
     * 统计 台账表数据量
     *
     * @param queryParam
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    @Override
    public Response<Long> count(QueryParam queryParam, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        try {
            Map<String, Object> param = Maps.newHashMap();
            param.put("unit", queryParam.getUnit());
            param.put("mouth", queryParam.getMouth());
            String sql = SQLUtil.countByParam(param, ledger, ledgerDictionaries);
            return Response.ok(ddlMapper.countByParam(sql));
        } catch (Exception e) {
            logger.error("count ledger failed. queryParam:{}, ledger:{}, cause:{}", queryParam, ledger, Throwables.getStackTraceAsString(e));
            return Response.fail("统计台账数据量失败");
        }
    }

    /**
     * 查询指定 台账的 指定ID 的数据
     *
     * @param ledger
     * @param ledgerDictionaries
     * @param ledgerDataId
     * @return
     */
    @Override
    public Response<LedgerDataSet> selectOne(Ledger ledger, List<LedgerDictionary> ledgerDictionaries, Long ledgerDataId) {
        try {
            String sql = SQLUtil.selectById(ledgerDataId, ledger, ledgerDictionaries);
            Map map = ddlMapper.selectById(sql);
            return Response.ok(assemblyLedgerData(ledgerDictionaries, map, ledgerDataId));
        } catch (Exception e) {
            logger.error("select one ledger failed. ledger:{}, ledgerDictionaries:{}, ledgerDataId:{}, cause:{}", ledger, ledgerDictionaries, ledger, Throwables.getStackTraceAsString(e));
            return Response.fail("查询指定台账指定id的数据失败");
        }
    }

    /**
     * 更新 台账 数据
     *
     * @param ledgerId
     * @param ledger
     * @param ledgerDictionaries
     * @param ledgerDataSet      @return
     */
    @Override
    public Response<Boolean> updateLedger(Long ledgerId, Ledger ledger, List<LedgerDictionary> ledgerDictionaries, LedgerDataSet ledgerDataSet) {
        try {
            Map<String, Object> map = Maps.newHashMap();
            ledgerDataSet.getLedgerData().forEach(lds -> map.put(lds.getName(), lds.getValue()));
            String sql = SQLUtil.updateById(Long.parseLong(ledgerDataSet.getId().toString()), ledger, ledgerDictionaries, map);
            logger.info("\n" + sql);
            ddlMapper.updateById(sql);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("update ledger failed. ledgerId:{}, ledgerDataSet:{}, cause:{}", ledgerId, ledgerDataSet, Throwables.getStackTraceAsString(e));
            return Response.fail("更新台账数据失败");
        }
    }

    /**
     * 查询 符合条件的 所有记录
     *
     * @param unitId
     * @param mouth
     * @param ledger
     * @param ledgerDictionaries
     * @return
     */
    @Override
    public Response<List<Map<String, Object>>> selectLedgerData(Long unitId, String mouth, Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        try {
            Map<String, Object> params = Maps.newHashMap();
            if (unitId != null && unitId != 0)
                params.put("unit_id", unitId);
            if (mouth != null && !"".equals(mouth))
                params.put("mouth", mouth);
            String sql = SQLUtil.selectListByParam(params, ledger, ledgerDictionaries);
            return Response.ok(ddlMapper.selectList(sql));
        } catch (Exception e) {
            logger.error("select ledger data failed. unit:{}, mouth:{}, ledger:{}, ld:{}, cause:{}", unitId, mouth, ledger, ledgerDictionaries, Throwables.getStackTraceAsString(e));
            return Response.fail("查询符合条件的所有记录失败");
        }
    }

    /**
     * 查询 原始表
     *
     * @param sql
     * @return
     */
    @Override
    public Response<List<Map<String, Object>>> selectPage(String sql) {
        try {
            logger.info("\n" + sql);
            return Response.ok(ddlMapper.selectPage(sql));
        } catch (Exception e) {
            logger.error("select source table failed. sql:{}, cause:{}", sql, Throwables.getStackTraceAsString(e));
            return Response.fail("查询原始表失败");
        }
    }

    /**
     * 插入 台账表
     *
     * @param ledger
     * @param ledgerDictionaries
     * @param data
     * @return
     */
    @Override
    public Response<Boolean> insertList(Ledger ledger, List<LedgerDictionary> ledgerDictionaries, List<Map<String, Object>> data) {
        try {
            String sql = SQLUtil.insertList(ledger, ledgerDictionaries, data);
            ddlMapper.insertList(sql);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            logger.error("insert into ledger failed. ledger:{}, data:{}, cause:{}", ledger, data, Throwables.getStackTraceAsString(e));
            return Response.fail("插入失败");
        }
    }

    private LedgerDataSet assemblyLedgerData(List<LedgerDictionary> ledgerDictionaries, Map map, Long ledgerDataId) {
        LedgerDataSet ledgerDataSet = new LedgerDataSet();
        List<LedgerData> ledgerData = Lists.newArrayList();
        final int[] index = {0};
        ledgerDictionaries.forEach(ld -> {
            LedgerData l = new LedgerData();
            l.setIndex(index[0]++);
            l.setName(ld.getSourceField());
            l.setValue(map.get(ld.getSourceField()));
            ledgerData.add(l);
        });
        ledgerDataSet.setLedgerData(ledgerData);
        ledgerDataSet.setId(ledgerDataId);

        return ledgerDataSet;
    }

    private List<LedgerDataSet> assemblyLedgerDataSet(List<Map> lds, List<LedgerDictionary> ledgerDictionaries) {
        List<LedgerDataSet> ledgerDataSets = Lists.newArrayList();

        lds.forEach(map -> {
            List<LedgerData> ledgerData = Lists.newArrayList();
            ledgerDictionaries.forEach(ledgerDictionary -> {
                LedgerData l = new LedgerData();
//                l.setName(ledgerDictionary.getSourceField());
                l.setValue(map.get(ledgerDictionary.getSourceField()));
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
