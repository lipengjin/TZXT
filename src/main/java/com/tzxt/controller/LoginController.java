package com.tzxt.controller;

import com.tzxt.dto.LoginUser;
import com.tzxt.exception.RestException;
import com.tzxt.model.User;
import com.tzxt.service.UserService;
import com.tzxt.util.AccountType;
import com.tzxt.util.Constants;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.MD5;
import com.tzxt.util.ResponseHelper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by wangshang on 17/5/7.
 */
@Controller
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/lock")
    public String lockScreen() {
        return "lock_screen";
    }

    @GetMapping(value = {"", "/login"})
    public String login() {
        return "login/login";
    }

    /**
     * 切换到 普通用户 登录
     *
     * @return
     */
    @GetMapping("/ordinaryLogin")
    public String ordinaryLogin() {
        return "login/ordinary_login";
    }

    @PostMapping(value = "/validate")
    public String validate(HttpServletRequest request, LoginUser loginUser) {
        // 用户密码验证
        User user = ResponseHelper.getOrThrow(userService.validate(loginUser));
        // 写入 session
        HttpSession session = request.getSession();
        session.setAttribute(Constants.SESSION_USER_ID, user.getId().toString());

        // 重定向到 主页
        return AccountType.ADMIN.getValue().equals(loginUser.getAccountType()) ? "redirect:/home" : "redirect:/ordinaryHome";
    }

    /**
     * 锁屏情况下 验证 密码
     *
     * @param password
     * @return
     */
    @PostMapping("reValidate")
    public String reValidate(String password) {
        // 验证密码
        User user = CurrentUser.get();
        if (user == null) {
            throw new RestException(HttpStatus.FORBIDDEN, "用户登录已过期，请重新登录");
        }
//        if (!user.getPassword().equals(MD5.encode(password))) {
 //           throw new RestException(HttpStatus.FORBIDDEN, "密码错误");
//        }

        // 重定向到主页
        return AccountType.ADMIN.getValue().equals(user.getAccountType()) ? "redirect:/home" : "redirect:/ordinaryHome";
    }

    @GetMapping(value = "/logout")
    public String logout(HttpServletRequest request) {
        // 用户下线
        User user = CurrentUser.get();
        // 0. 取出 session 中存放的 userId， 判断不为 null 就进行 logout 操作，否则不做操作
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 1. 清空 session
            session.invalidate();
            // 重定向到登录界面

            return AccountType.ADMIN.getValue().equals(user.getAccountType()) ? "redirect:/" : "redirect:/ordinaryLogin";
        }
        return "redirect:/";
    }
}
