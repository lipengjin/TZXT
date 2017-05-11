package com.tzxt.service.impl;

import com.tzxt.mapper.DDLMapper;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DDLService;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wangshang on 17/5/11.
 */
@Service
public class DDLServiceImpl implements DDLService {

    private final SqlSessionTemplate sessionTemplate;

    private final DDLMapper ddlMapper;

    public DDLServiceImpl(DDLMapper ddlMapper,
                          SqlSessionTemplate sessionTemplate) {
        this.ddlMapper = ddlMapper;
        this.sessionTemplate = sessionTemplate;
    }


    /**
     * 创建 表
     *
     * @param name
     * @param ledgerDictionaries
     * @return
     */
    @Override
    public Boolean createTable(String name, List<LedgerDictionary> ledgerDictionaries) {
        return null;
    }

    /**
     * 检测 表是否存在
     *
     * @param name
     * @return
     */
    @Override
    public Boolean exist(String name) {
        return null;
    }
}
