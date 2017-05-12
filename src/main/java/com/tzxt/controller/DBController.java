package com.tzxt.controller;

import com.tzxt.exception.RestException;
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

    public DBController(LedgerService ledgerService,
                        LedgerDictionaryService ledgerDictionaryService,
                        DBService DBService) {
        this.ledgerService = ledgerService;
        this.ledgerDictionaryService = ledgerDictionaryService;
        this.DBService = DBService;
    }

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

    @GetMapping("/pullData")
    @ResponseBody
    public Boolean pullData(@RequestParam Long ledgerId) {

        System.out.println(ledgerId);
        throw new RestException(HttpStatus.EXPECTATION_FAILED, "拉取数据过程中出现错误");
    }

}
