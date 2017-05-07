package com.tzxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by dell pc on 2017/5/7.
 */
@Controller
public class HomeController {

    @RequestMapping(value = {"", "/home"}, method= RequestMethod.GET)
    public String home(){
        return "index";
    }

}
