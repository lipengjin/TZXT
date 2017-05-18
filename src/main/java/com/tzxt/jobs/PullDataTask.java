package com.tzxt.jobs;

import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DBService;
import com.tzxt.service.LedgerService;
import com.tzxt.util.ResponseHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangshang on 17/5/17.
 */
@Component
public class PullDataTask {

    private ExecutorService executorService = Executors.newCachedThreadPool();

    private final Worker worker;
    private final LedgerService ledgerService;

    public PullDataTask(Worker worker,
                        LedgerService ledgerService) {
        this.worker = worker;
        this.ledgerService = ledgerService;
    }

    /**
     * 执行 任务
     * <p>
     *
     * @param ledger
     */
    public void runTask(Ledger ledger, List<LedgerDictionary> ledgerDictionaries) {
        worker.setLedger(ledger);
        worker.setLedgerDictionaries(ledgerDictionaries);
        worker.setOnLoadFinish(new OnLoadFinish() {
            @Override
            public void onSuccess() {
                Ledger l = new Ledger();
                l.setPullStatus(Ledger.PullStatus.FINISHED.ordinal());
                l.setId(ledger.getId());
                ResponseHelper.getOrThrow(ledgerService.update(l));
            }

            @Override
            public void onFailed() {
                Ledger l = new Ledger();
                l.setPullStatus(Ledger.PullStatus.FAILED.ordinal());
                l.setId(ledger.getId());
                ResponseHelper.getOrThrow(ledgerService.update(l));
                ledgerService.update(l);
            }
        });
        executorService.submit(worker);
    }

    /**
     * 程序关闭时,需手动销毁线程池
     */
    @PreDestroy
    public synchronized void destory() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }


}
