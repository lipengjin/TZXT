package com.tzxt.dto;

import lombok.Data;

import java.util.List;

/**
 * Created by wangshang on 17/5/12.
 */
@Data
public class LedgerDataSet {

    private Object id;

    private List<LedgerData> ledgerData;
}
