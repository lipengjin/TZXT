package com.tzxt.controller;

import com.github.pagehelper.PageInfo;
import com.tzxt.dto.LedgerDetail;
import com.tzxt.model.Ledger;
import com.tzxt.service.LedgerService;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.ResponseHelper;
import org.assertj.core.util.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * 台账 管理
 *
 * Created by wangshang on 17/5/8.
 */
@Controller
@RequestMapping("/ledger")
public class LedgerController {

    private final LedgerService ledgerService;

    public LedgerController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }


    @GetMapping
    public ModelAndView ledgers(@RequestParam(required = false) Integer pageNo,
                                @RequestParam(required = false) Integer pageSize,
                                @RequestParam(required = false) String name) {

        // 1. 权限检测

        // 2. 执行查询
        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.pageLedger(pageNo, pageSize, name));

        ModelAndView result = new ModelAndView("/ledger_manage/ledger");
        result.addObject("ledgerPage", ledgers);
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    /**
     * 创建 台账
     *
     * @return
     */
    @GetMapping("/create")
    public ModelAndView create() {
        // 1. 权限检测

        // 2. 重定向 到新建页面
        ModelAndView result = new ModelAndView("/ledger_manage/new_ledger");
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    /**
     * 保存创建
     * @return
     */
    @PostMapping("/save")
    public ModelAndView save(LedgerDetail ledgerDetail) {

        // 重定向到 台账详情 页面
        ModelAndView result = new ModelAndView("/ledger_manage/ledger_detail");
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    /**
     * 查看 台账的 详细信息
     *
     * @param ledgerId
     * @return
     */
    @GetMapping("/detail/{ledgerId}")
    public ModelAndView detail(@PathVariable Long ledgerId) {

        // 重定向 到 台账详情 页面
        ModelAndView result = new ModelAndView("/ledger_manage/ledger_detail");
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    /**
     * 删除 台账
     *
     * @param ledgerId
     * @return
     */
    @PutMapping("/delete/{ledgerId}")
    public ModelAndView delete(@PathVariable Long ledgerId){

        ModelAndView result = new ModelAndView("");
        result.addObject("currUser", CurrentUser.get());

        return result;
    }
}
