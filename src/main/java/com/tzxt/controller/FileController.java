package com.tzxt.controller;


import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.DBService;
import com.tzxt.service.LedgerDictionaryService;
import com.tzxt.service.LedgerService;
import com.tzxt.util.ExcelExport;
import com.tzxt.util.ResponseHelper;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

/**
 * Created by wangshang on 17/5/13.
 */
@Controller
@RequestMapping("/excel")
public class FileController {
    private final LedgerService ledgerService;
    private final LedgerDictionaryService ledgerDictionaryService;
    private final DBService dbService;

    public FileController(LedgerService ledgerService,
                          LedgerDictionaryService ledgerDictionaryService,
                          DBService dbService) {
        this.ledgerService = ledgerService;
        this.ledgerDictionaryService = ledgerDictionaryService;
        this.dbService = dbService;
    }

    @GetMapping("/export/{ledgerId}")
    public void excel(HttpServletResponse response,
                      @PathVariable Long ledgerId,
                      @RequestParam(required = false) Long unitId,
                      @RequestParam(required = false) String mouth) throws Exception {

        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));

        List<String> titles = Lists.newArrayList();
        titles.add("ID");
//        ledgerDictionaries.forEach(ld -> titles.add(ld.getFieldName()));
        ledgerDictionaries.forEach(ld -> titles.add(ld.getLdComment()));
        titles.add("创建时间");
        titles.add("更新时间");
        List<String> titleKeys = Lists.newArrayList();
        titleKeys.add("id");
        ledgerDictionaries.forEach(ld -> titleKeys.add(ld.getFieldName()));
        titleKeys.add("create_at");
        titleKeys.add("update_at");
        List<Map<String, Object>> maps = ResponseHelper.getOrThrow(dbService.selectLedgerData(unitId, mouth, ledger, ledgerDictionaries));

        String fileName = mouth != null && !"".equals(mouth) ? ledger.getName() + "_" + mouth + ".xlsx" : ledger.getName() + ".xlsx";
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ExcelExport.doExport(ledger.getName(), titles, titleKeys, maps, bao);
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
        IOUtils.write(bao.toByteArray(), response.getOutputStream());
    }

}
