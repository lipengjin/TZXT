package com.tzxt.controller;

import com.tzxt.exception.RestException;
import com.tzxt.jobs.PullDataTask;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DBService;
import com.tzxt.service.LedgerDictionaryService;
import com.tzxt.service.LedgerService;
import com.tzxt.util.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 专门 处理 表级别 操作的 controller
 * <p>
 * Created by wangshang on 17/5/11.
 */
@Controller
@RequestMapping("/db")
public class DBController {

    private final LedgerService ledgerService;
    private final LedgerDictionaryService ledgerDictionaryService;
    private final DBService DBService;
    private final PullDataTask pullDataTask;

    public DBController(LedgerService ledgerService,
                        LedgerDictionaryService ledgerDictionaryService,
                        DBService DBService,
                        PullDataTask pullDataTask) {
        this.ledgerService = ledgerService;
        this.ledgerDictionaryService = ledgerDictionaryService;
        this.DBService = DBService;
        this.pullDataTask = pullDataTask;
    }

    /**
     * 创建 台账表
     *
     * @param ledgerId
     * @return
     */
    @GetMapping("/createLedger")
    @ResponseBody
    public Boolean createLedger(@RequestParam Long ledgerId) {

        // 1. 权限检测

        // 2. 查询 台账 元数据
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));

        // 3. 建表
        if (ResponseHelper.getOrThrow(DBService.exist(ledger.getTableName()))) {
            throw new RestException(HttpStatus.BAD_REQUEST, "此台账表已经建好，不要重复创建");
        }
        return ResponseHelper.getOrThrow(DBService.createTable(ledger, ledgerDictionaries));
    }

    /**
     * 管理员 启动 拉取数据的 线程
     *
     * @param ledgerId 操作的 台账 id
     * @param start    拉取时间 区间 开始
     * @param end      拉取时间 区间 结束
     * @return
     */
    @GetMapping("/pullData")
    @ResponseBody
    public Boolean pullData(@RequestParam Long ledgerId,
                            @RequestParam(required = false) String start,
                            @RequestParam(required = false) String end) {

        // 1. 权限检测

        // 2. 对比检测 这里的时间区间与 表中的时间区间 以及 拉取数据状态

        // 2. 更新 时间区间
        Ledger ledger = new Ledger();
        ledger.setStartDate(start);
        ledger.setEndDate(end);
        ledger.setUpdateAt(new Date());
        ledger.setId(ledgerId);
        ledger.setPullStatus(Ledger.PullStatus.PULLING.ordinal());
        ledgerService.update(ledger);
        // 3. 开启任务， 如果没有 异常出现 返回前端 TRUE
        Ledger ledger1 = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        pullDataTask.runTask(ledger1, ledgerDictionaries);
        return Boolean.TRUE;
    }

}
