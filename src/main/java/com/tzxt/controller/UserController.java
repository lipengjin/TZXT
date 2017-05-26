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

import com.tzxt.dto.UnitAndRoleAndAuthDto;
import com.tzxt.dto.UserManageDto;
import com.tzxt.model.Ledger;
import com.tzxt.model.Role;
import com.tzxt.model.RoleAuths;
import com.tzxt.model.Unit;
import com.tzxt.model.User;
import com.tzxt.model.UserAuth;
import com.tzxt.service.LedgerService;
import com.tzxt.service.RoleAuthService;
import com.tzxt.service.RoleService;
import com.tzxt.service.UnitService;
import com.tzxt.service.UserService;
import com.tzxt.util.AccountType;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.MD5;
import com.tzxt.util.ResponseHelper;
import com.tzxt.util.SecurityHelper;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final LedgerService ledgerService;

    private final UnitService unitService;

    private final UserService userService;

    private final RoleService roleService;

    public UserController(LedgerService ledgerService,
                          UnitService unitService,
                          UserService userService,
                          RoleService roleService) {
        this.ledgerService = ledgerService;
        this.unitService = unitService;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(User user) {
        ModelAndView result = new ModelAndView("redirect:/login");
        result.addObject("currUser", new User());
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView createUser(User user) {
        SecurityHelper.chechAdmin();

        Long roleId = user.getRoleId();
        user.setRole(roleService.findById(roleId).getData().getName());
        Long unitId = user.getUnitId();
        user.setUnit(unitService.findById(unitId).getData().getName());
        user.setAccountType(2);
        user.setPassword(MD5.encode(user.getPassword()));
        user.setLocked(false);
        user.setUpdateAt(new Date());
        user.setCreateAt(new Date());

        userService.insert(user);
        ModelAndView result = new ModelAndView("redirect:/users/manage");
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

    @GetMapping(value = "/add-user")
    public ModelAndView addUser() {
        SecurityHelper.chechAdmin();

        UnitAndRoleAndAuthDto unitAndRoleAndAuthDto = new UnitAndRoleAndAuthDto();

        List<Role> roles = roleService.getAll().getData();
        List<Unit> units = unitService.selectAll().getData();
        List<UserAuth> userAuths = User.Auth.authList();
        unitAndRoleAndAuthDto.setUserAuths(userAuths);
        unitAndRoleAndAuthDto.setRoles(roles);
        unitAndRoleAndAuthDto.setUnits(units);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unitAndRoleAndAuthDto", unitAndRoleAndAuthDto);
        modelAndView.setViewName("/userManage/add_user");
        modelAndView.addObject("currUser", CurrentUser.get());
        return modelAndView;
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
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        ModelAndView result = new ModelAndView();
        result.addObject("currUser", CurrentUser.get());

        if (AccountType.ADMIN.getValue().equals(user.getAccountType())) {
            result.setViewName("/user/user_profile");
        } else {
            result.setViewName("/user/ordinary_user_profile");
            result.addObject("ledgers", ledgers);
        }
        return result;
    }

    @GetMapping(value = "/manage")
    public ModelAndView userManage() {
        SecurityHelper.chechAdmin();

        List<User> users = userService.getAll().getData();
        List<UserManageDto> userManageDtos = new ArrayList<>();
        for (User user : users) {
            UserManageDto userManageDto = new UserManageDto();
            userManageDto.setId(user.getId());
            userManageDto.setUserName(user.getUserName());
            userManageDto.setRealName(user.getRealName());
            userManageDto.setUnitName(user.getUnit());
            userManageDto.setLocked(user.getLocked().toString());
            userManageDto.setRole(user.getRole());
            userManageDto.setAuth(User.Auth.fromValue(user.getAuth()).getDisplay());
            userManageDtos.add(userManageDto);
        }

        ModelAndView result = new ModelAndView();
        result.addObject("userManageDtos", userManageDtos);
        result.setViewName("/userManage/user_manage");
        result.addObject("currUser", CurrentUser.get());
        return result;
    }

}