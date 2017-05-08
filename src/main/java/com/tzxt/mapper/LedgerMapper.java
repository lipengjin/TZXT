package com.tzxt.mapper;

import com.tzxt.model.Ledger;
import com.tzxt.util.MyMapper;

import java.util.List;

/**
 * Created by wangshang on 17/5/8.
 */
public interface LedgerMapper extends MyMapper<Ledger> {
    List<Ledger> selectByParams(String name);
}
