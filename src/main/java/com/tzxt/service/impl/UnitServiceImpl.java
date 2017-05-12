package com.tzxt.service.impl;

import com.google.common.base.Throwables;
import com.tzxt.mapper.UnitMapper;
import com.tzxt.model.Unit;
import com.tzxt.service.UnitService;
import com.tzxt.util.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangshang on 17/5/12.
 */
@Service
public class UnitServiceImpl implements UnitService {

    private Logger logger = LoggerFactory.getLogger(UnitServiceImpl.class);

    private final UnitMapper unitMapper;

    public UnitServiceImpl(UnitMapper unitMapper) {
        this.unitMapper = unitMapper;
    }

    @Override
    public Response<List<Unit>> selectAll() {
        try {
            return Response.ok(unitMapper.selectAll());
        } catch (Exception e) {
            logger.error("select all units failed. cause:{}", Throwables.getStackTraceAsString(e));
            return Response.fail("查询所有单位失败");
        }
    }
}
