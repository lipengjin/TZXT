package com.tzxt.mapper;

import com.tzxt.model.LedgerDictionary;
import com.tzxt.util.MyMapper;

import java.util.List;

/**
 * Created by wangshang on 17/5/10.
 */
public interface LedgerDictionaryMapper extends MyMapper<LedgerDictionary> {

    List<LedgerDictionary> selectByLedgerId(Long ledgerId);

    void deleteByLedgerId(Long ledgerId);
}
