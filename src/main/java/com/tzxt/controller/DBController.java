package com.tzxt.controller;

import com.tzxt.exception.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 专门 处理 表级别 操作的 controller
 * <p>
 * Created by wangshang on 17/5/11.
 */
@Controller
@RequestMapping("/db")
public class DBController {


    @GetMapping("/createLedger")
    @ResponseBody
    public Boolean createLedger(@RequestParam Long ledgerId) {

        System.out.println(ledgerId);

        return Boolean.TRUE;
    }

    @GetMapping("/pullData")
    @ResponseBody
    public Boolean pullData(@RequestParam Long ledgerId) {

        System.out.println(ledgerId);
        throw new RestException(HttpStatus.EXPECTATION_FAILED, "拉取数据过程中出现错误");
    }

}
