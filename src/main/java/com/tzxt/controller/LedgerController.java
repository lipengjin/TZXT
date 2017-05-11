package com.tzxt.controller;

import com.github.pagehelper.PageInfo;
import com.tzxt.dto.LedgerDetail;
import com.tzxt.dto.UpdateParam;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.service.LedgerDictionaryService;
import com.tzxt.service.LedgerService;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.ResponseHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * 台账 管理
 * <p>
 * Created by wangshang on 17/5/8.
 */
@Controller
@RequestMapping("/ledger")
public class LedgerController {

    private final LedgerService ledgerService;
    private final LedgerDictionaryService ledgerDictionaryService;

    public LedgerController(LedgerService ledgerService,
                            LedgerDictionaryService ledgerDictionaryService) {
        this.ledgerService = ledgerService;
        this.ledgerDictionaryService = ledgerDictionaryService;
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
        result.addObject("ledgerPage", new PageInfo<>(ledgers));
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
     *
     * @return
     */
    @PostMapping("/save")
    public String save(LedgerDetail ledgerDetail) {
        // 1. 权限检测

        // 2. 数据合法性检测
        System.out.println(ledgerDetail);

        // 3. 执行 保存操作
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.create(ledgerDetail.getLedger()));
        ledgerDetail.getLedgerDictionaries().forEach(ledgerDictionary -> {
            ledgerDictionary.setLedgerId(ledger.getId());
            // TODO: 17/5/10 这里先模拟填写 字段对应的原始数据表的 字段名
            // TODO: 17/5/10 如果 动态选择 原始表的 字段，需要提供原始表的表结构或者原始数据库的连接方式自行获取其表结构。 同时前端界面也需要修改
            ledgerDictionary.setSourceField("test_source_field");
        });
        ResponseHelper.getOrThrow(ledgerDictionaryService.saveAll(ledgerDetail.getLedgerDictionaries()));
        // 4. 重定向到 台账详情 页面
        return "redirect:/ledger/detail/" + ledger.getId();
    }

    /**
     * 查看 台账的 详细信息
     *
     * @param ledgerId
     * @return
     */
    @GetMapping("/detail/{ledgerId}")
    public ModelAndView detail(@PathVariable Long ledgerId) {

        LedgerDetail ledgerDetail = new LedgerDetail();
        ledgerDetail.setLedger(ResponseHelper.getOrThrow(ledgerService.getById(ledgerId)));
        ledgerDetail.setLedgerDictionaries(ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId)));
        // 重定向 到 台账详情 页面
        ModelAndView result = new ModelAndView("/ledger_manage/ledger_detail");
        result.addObject("ledgerDetail", ledgerDetail);
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    /**
     * 更新台账 名称
     *
     * @param
     */
    @PostMapping("/updateLedgerName/{ledgerId}")
    @ResponseBody
    public Boolean updateLedgerName(UpdateParam param,
                                    @PathVariable Long ledgerId) {

        // 1. 权限检测

        // 2. 执行更新
        return ResponseHelper.getOrThrow(ledgerService.update(ledger(param, ledgerId)));
    }

    private Ledger ledger(UpdateParam param, Long ledgerId) {
        Ledger ledger = new Ledger();
        ledger.setId(ledgerId);
        if ("ledgerName".equals(param.getName()))
            ledger.setName(param.getValue());
        else if ("ledgerComment".equals(param.getName()))
            ledger.setComment(param.getValue());
        ledger.setUpdateAt(new Date());
        return ledger;
    }

    /**
     * 更新台账 注释
     *
     * @param
     */
    @PostMapping("/updateLedgerComment/{ledgerId}")
    @ResponseBody
    public Boolean updateLedgerComment(UpdateParam param,
                                       @PathVariable Long ledgerId) {
        // 1. 权限检测

        // 2. 执行更新
        return ResponseHelper.getOrThrow(ledgerService.update(ledger(param, ledgerId)));

    }

    /**
     * 更新 台账 数据字典
     *
     * @param ledgerDictionaryId
     * @return
     */
    @GetMapping("/updateLedgerDictionary/{ledgerDictionaryId}")
    public ModelAndView updateLedgerDictionary(@PathVariable Long ledgerDictionaryId) {
        // 1. 权限检测

        LedgerDictionary dictionary = ResponseHelper.getOrThrow(ledgerDictionaryService.selectById(ledgerDictionaryId));

        // 2. 重定向 到新建页面
        ModelAndView result = new ModelAndView("/ledger_manage/update_ledger_dictionary");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledgerDictionary", dictionary);
        return result;
    }

    /**
     * 保存 更新台账 数据字典
     *
     * @param ledgerDictionary
     * @return
     */
    @PostMapping("/saveUpdateLedgerDictionary")
    public String saveUpdateLedgerDictionary(LedgerDictionary ledgerDictionary) {

        // 1. 权限检测

        // 2. 更新
        ledgerDictionary.setUpdateAt(new Date());
        ResponseHelper.getOrThrow(ledgerDictionaryService.update(ledgerDictionary));
        return "redirect:/ledger/detail/" + ledgerDictionary.getLedgerId();
    }

    /**
     * 删除 台账
     *
     * @param ledgerId
     * @return
     */
    @PutMapping("/delete/{ledgerId}")
    public ModelAndView delete(@PathVariable Long ledgerId) {

        // 1. 权限检测

        // 2. 删除 表

        // 3. 删除 台账信息
        ModelAndView result = new ModelAndView("");
        result.addObject("currUser", CurrentUser.get());

        return result;
    }

    /**
     * 查看选定的 台账 的数据
     *
     * @return
     */
    @GetMapping("/check/{ledgerId}")
    public ModelAndView ledger(@PathVariable Long ledgerId) {

        System.out.println(ledgerId);
        ModelAndView result = new ModelAndView("/ledger/ledger");
        result.addObject("currUser", CurrentUser.get());

        return result;
    }
}
