package com.tzxt.controller;

import com.tzxt.model.Ledger;
import com.tzxt.model.User;
import com.tzxt.service.LedgerService;
import com.tzxt.util.AccountType;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.ResponseHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by dell pc on 2017/5/7.
 */
@Controller
public class HomeController {

    private final LedgerService ledgerService;

    public HomeController(LedgerService ledgerService) {
        this.ledgerService = ledgerService;
    }

    @GetMapping(value = "/home")
    public ModelAndView home() {

        User user = CurrentUser.get();
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        ModelAndView result = new ModelAndView();
        result.addObject("currUser", CurrentUser.get());

        if (AccountType.ADMIN.getValue().equals(user.getAccountType())) {
            result.setViewName("/index/index");
        } else {
            result.setViewName("/index/ordinary_index");
            result.addObject("ledgers", ledgers);
        }
        return result;
    }

    @GetMapping(value = "/ordinaryHome")
    public ModelAndView ordinaryHome() {

        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        ModelAndView result = new ModelAndView("/index/ordinary_index");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledgers", ledgers);

        return result;
    }

}
