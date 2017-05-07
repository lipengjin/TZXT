package com.tzxt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by wangshang on 17/5/7.
 */
@Controller
public class LoginController {

    @RequestMapping(value = {"", "/login"}, method= RequestMethod.GET)
    public String login(){
        return "login";
    }
}
