/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.tzxt.controller;

import com.tzxt.model.User;
import com.tzxt.util.AccountType;
import com.tzxt.util.CurrentUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/users")
public class UserController {


    @PostMapping(value = "/register")
    public ModelAndView register(User user) {
        ModelAndView result = new ModelAndView("redirect:/login");
        result.addObject("currUser", new User());
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView createUser(User user) {

        return null;
    }

    /**
     * 查看个人详细信息
     *
     * @return
     */
    @GetMapping(value = "profile")
    public ModelAndView user() {
        // 根据 用户 的角色 返回不同的界面
        User user = CurrentUser.get();
        ModelAndView result = AccountType.ADMIN.getValue().equals(user.getAccountType()) ?
                new ModelAndView("/user/user_profile") :
                new ModelAndView("/user/ordinary_user_profile");
        result.addObject("currUser", user);
        return result;
    }
}
