package com.tzxt.dto;

import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import lombok.Data;

import java.util.List;

/**
 * 台账 详细信息
 * 包括 台账 名、注释、所有字段信息
 * Created by wangshang on 17/5/8.
 */
@Data
public class LedgerDetail {

    private Ledger ledger;

    private List<LedgerDictionary> ledgerDictionaries;

}
