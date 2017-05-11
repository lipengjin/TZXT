package com.tzxt.controller;

import com.tzxt.util.CurrentUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by dell pc on 2017/5/7.
 */
@Controller
public class HomeController {

    @GetMapping(value = "/home")
    public ModelAndView home() {
        ModelAndView result = new ModelAndView("/index/index");
        result.addObject("currUser", CurrentUser.get());

        return result;
    }

    @GetMapping(value = "/ordinaryHome")
    public ModelAndView ordinaryHome() {

        ModelAndView result = new ModelAndView("/index/ordinary_index");
        result.addObject("currUser", CurrentUser.get());

        return result;
    }

}
