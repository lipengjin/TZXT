package com.tzxt.controller;

import com.github.pagehelper.PageInfo;
import com.tzxt.dto.LedgerDataSet;
import com.tzxt.dto.LedgerDetail;
import com.tzxt.dto.QueryParam;
import com.tzxt.dto.UpdateParam;
import com.tzxt.model.Ledger;
import com.tzxt.model.LedgerDictionary;
import com.tzxt.model.Unit;
import com.tzxt.service.DBService;
import com.tzxt.service.LedgerDictionaryService;
import com.tzxt.service.LedgerService;
import com.tzxt.service.UnitService;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.LedgerDataPageInfo;
import com.tzxt.util.ResponseHelper;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    private final UnitService unitService;
    private final DBService dbService;

    public LedgerController(LedgerService ledgerService,
                            LedgerDictionaryService ledgerDictionaryService,
                            UnitService unitService,
                            DBService dbService) {
        this.ledgerService = ledgerService;
        this.ledgerDictionaryService = ledgerDictionaryService;
        this.unitService = unitService;
        this.dbService = dbService;
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

        // 3. 这里应该是 动态 获取 可选数据源表
        List<String> sourceTables = Lists.newArrayList("source1");
        // 4. 这里实际上是应该 动态获取 实际数据源指定数据表的 表字段信息，但这里 就用一个 list来模拟一下吧。
        List<String> sourceFields = Lists.newArrayList("field1", "field2", "field3", "field4", "field5");

        ModelAndView result = new ModelAndView("/ledger_manage/new_ledger");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("sourceTables", sourceTables);
        result.addObject("sourceFields", sourceFields);
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
        checkLedgerDetail(ledgerDetail);

        // 3. 执行 保存操作
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.create(ledgerDetail.getLedger()));
        ledgerDetail.getLedgerDictionaries().forEach(ledgerDictionary -> {
            ledgerDictionary.setFieldType(ledgerDictionary.getFieldType().toUpperCase());
            ledgerDictionary.setLedgerId(ledger.getId());
        });
        ResponseHelper.getOrThrow(ledgerDictionaryService.saveAll(ledgerDetail.getLedgerDictionaries()));
        // 4. 重定向到 台账详情 页面
        return "redirect:/ledger/detail/" + ledger.getId();
    }

    private void checkLedgerDetail(LedgerDetail ledgerDetail) {
        // 检测 Ledger name、原数据表

        // 检测 Ledger 数据字典 数据类型的合法性、对应原始数据表的 字段的合法性（是否存在）、字段名称 唯一性
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
        Boolean exist = ResponseHelper.getOrThrow(dbService.exist(ledgerDetail.getLedger().getTableName()));
        // 重定向 到 台账详情 页面
        ModelAndView result = new ModelAndView("/ledger_manage/ledger_detail");
        result.addObject("ledgerDetail", ledgerDetail);
        result.addObject("currUser", CurrentUser.get());
        result.addObject("exist", exist ? "disabled" : "");
        result.addObject("style", exist ? "" : "display: none;");
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
     * 检测表是否存在及是否有数据
     *
     * @param ledgerId
     * @return
     */
    @GetMapping("/tableNotExist/{ledgerId}")
    @ResponseBody
    public Boolean tableNotExist(@PathVariable Long ledgerId) {

        // 检测表是否存在
        Ledger ledger = ResponseHelper.ajaxGetOrThrow(ledgerService.getById(ledgerId));
        Boolean exist = ResponseHelper.ajaxGetOrThrow(dbService.exist(ledger.getTableName()));
        if (!exist) {
            return Boolean.TRUE;
        }
        // 检测表中是否有数据
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.ajaxGetOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        Long count = ResponseHelper.ajaxGetOrThrow(dbService.count(null, ledger, ledgerDictionaries));
        if (count > 0L) {
            return Boolean.FALSE;
        } else {
            return Boolean.TRUE;
        }

    }

    /**
     * 删除 台账
     *
     * @param ledgerId
     * @return
     */
    @DeleteMapping("/delete/{ledgerId}")
    @ResponseBody
    public Boolean delete(@PathVariable Long ledgerId) {

        // 1. 权限检测

        // 2. 删除 表
        Ledger ledger = ResponseHelper.ajaxGetOrThrow(ledgerService.getById(ledgerId));
        Boolean exist = ResponseHelper.ajaxGetOrThrow(dbService.exist(ledger.getTableName()));
        if (exist) {
            ResponseHelper.ajaxGetOrThrow(dbService.dropTable(ledger));
        }

        // 3. 删除 台账信息
        ResponseHelper.ajaxGetOrThrow(ledgerService.deleteLedger(ledgerId));
        ResponseHelper.ajaxGetOrThrow(ledgerDictionaryService.deleteByLedgerId(ledgerId));

        return Boolean.TRUE;
    }


    /**
     * 编辑 台账 数据
     *
     * @param ledgerDataId
     * @return
     */
    @GetMapping("/data/{ledgerId}/{ledgerDataId}")
    public ModelAndView editLedgerData(@PathVariable Long ledgerId,
                                       @PathVariable Long ledgerDataId) {

        // 1. 权限检测
        System.out.println(ledgerDataId);

        // 2. 返回模型视图
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        LedgerDataSet ledgerDataSet = ResponseHelper.getOrThrow(dbService.selectOne(ledger, ledgerDictionaries, ledgerDataId));

        ModelAndView result = new ModelAndView("/ledger/edit_ledger");
        result.addObject("ledgers", ledgers);
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledger", ledger);
        result.addObject("ledgerDictionaries", ledgerDictionaries);
        result.addObject("ledgerDataSet", ledgerDataSet);

        return result;
    }

    /**
     * 保存 修改的 台账数据
     *
     * @param ledgerId
     * @param ledgerDataSet
     * @return
     */
    @PostMapping("/saveLedgerData/{ledgerId}")
    public String saveLedgerData(@PathVariable Long ledgerId,
                                 LedgerDataSet ledgerDataSet) {

        // 1. 权限检测

        // 2. update
        System.out.println(ledgerId);
        System.out.println(ledgerDataSet);
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        ResponseHelper.getOrThrow(dbService.updateLedger(ledgerId, ledger, ledgerDictionaries, ledgerDataSet));

        return "redirect:/ledger/check/" + ledgerId;
    }

    /**
     * 查看选定的 台账 的数据
     * 处理 分页和初次进入 此页面的 get 请求
     *
     * @return
     */
    @GetMapping("/check/{ledgerId}")
    public ModelAndView ledger(@PathVariable Long ledgerId,
                               @RequestParam(required = false) Integer pageNo,
                               @RequestParam(required = false) Integer pageSize,
                               @RequestParam(required = false) String unit,
                               @RequestParam(required = false) String mouth) {

        // 1. 权限检测

        pageNo = pageNo == null ? 1 : pageNo;
        pageSize = pageSize == null ? 10 : pageSize;
        // 2. 返回 模型视图
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        List<Unit> units = ResponseHelper.getOrThrow(unitService.selectAll());
        List<LedgerDataSet> ledgerDataSets = ResponseHelper.getOrThrow(dbService.pageLedgerData(pageNo, pageSize, new QueryParam(unit, mouth), ledger, ledgerDictionaries));
        Long total = ResponseHelper.getOrThrow(dbService.count(new QueryParam(unit, mouth), ledger, ledgerDictionaries));

        ModelAndView result = new ModelAndView("/ledger/ledger");
        result.addObject("ledgers", ledgers);
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledger", ledger);
        result.addObject("ledgerDictionaries", ledgerDictionaries);
        result.addObject("units", units);
        result.addObject("queryParam", new QueryParam(unit, mouth));
        result.addObject("ledgerDataPageInfo", new LedgerDataPageInfo(ledgerDataSets, pageNo, pageSize, total));

        return result;
    }

    /**
     * 查看选定的 台账 的数据
     * 处理表单提交的 post 请求
     *
     * @return
     */
    @PostMapping("/check/{ledgerId}")
    public ModelAndView checkLedger(@PathVariable Long ledgerId, QueryParam queryParam) {
        // 1. 权限检测

        queryParam.setUnit(queryParam.getUnit() != null || !"0".equals(queryParam.getUnit()) || !"".equals(queryParam.getUnit()) ? queryParam.getUnit() : null);
        // 2. 返回 模型视图
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        Ledger ledger = ResponseHelper.getOrThrow(ledgerService.getById(ledgerId));
        List<LedgerDictionary> ledgerDictionaries = ResponseHelper.getOrThrow(ledgerDictionaryService.selectByLedgerId(ledgerId));
        List<Unit> units = ResponseHelper.getOrThrow(unitService.selectAll());
        List<LedgerDataSet> ledgerDataSets = ResponseHelper.getOrThrow(dbService.pageLedgerData(1, 10, queryParam, ledger, ledgerDictionaries));
        Long total = ResponseHelper.getOrThrow(dbService.count(queryParam, ledger, ledgerDictionaries));


        ModelAndView result = new ModelAndView("/ledger/ledger");
        result.addObject("ledgers", ledgers);
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledger", ledger);
        result.addObject("ledgerDictionaries", ledgerDictionaries);
        result.addObject("units", units);
        result.addObject("queryParam", queryParam);
        result.addObject("ledgerDataPageInfo", new LedgerDataPageInfo(ledgerDataSets, 1, 10, total));


        return result;
    }
}
