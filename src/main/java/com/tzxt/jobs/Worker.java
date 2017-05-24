package com.tzxt.jobs;

import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DBService;
import com.tzxt.util.ResponseHelper;
import com.tzxt.util.SQLUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by wangshang on 17/5/17.
 */
@Component
@Scope("prototype")
public class Worker implements Runnable {

    @Getter
    @Setter
    private OnLoadFinish onLoadFinish;

    @Getter
    @Setter
    private Ledger ledger;

    @Getter
    @Setter
    private List<LedgerDictionary> ledgerDictionaries;

    private final DBService dbService;

    public Worker(DBService dbService) {
        this.dbService = dbService;
    }



    @Override
    public void run() {
        try {

            int pageSize = 100;
            int pageNo = 0;
            boolean exist = true;
            while (exist) {
                String sql = SQLUtil.selectPage(++pageNo, pageSize, ledger, ledgerDictionaries);
                List<Map<String, Object>> data = ResponseHelper.getOrThrow(dbService.selectPage(sql));
                exist = data.size() == pageSize;
                ResponseHelper.getOrThrow(dbService.insertList(ledger, ledgerDictionaries, data));
            }


            if (onLoadFinish != null) {
                onLoadFinish.onSuccess();
            }
        } catch (Exception e) {
            onLoadFinish.onFailed();
        }
    }
}
