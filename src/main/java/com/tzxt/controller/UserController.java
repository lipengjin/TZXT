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
import com.tzxt.service.LedgerService;
import com.tzxt.service.RoleAuthService;
import com.tzxt.service.RoleService;
import com.tzxt.service.UnitService;
import com.tzxt.service.UserService;
import com.tzxt.util.AccountType;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.ResponseHelper;
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

    private final RoleAuthService roleAuthService;

    public UserController(LedgerService ledgerService, UnitService unitService, UserService userService, RoleService roleService, RoleAuthService roleAuthService) {
        this.ledgerService = ledgerService;
        this.unitService=unitService;
        this.userService=userService;
        this.roleService = roleService;
        this.roleAuthService = roleAuthService;
    }

    @PostMapping(value = "/register")
    public ModelAndView register(User user) {
        ModelAndView result = new ModelAndView("redirect:/login");
        result.addObject("currUser", new User());
        return result;
    }

    @PostMapping(value = "/create")
    public ModelAndView createUser(User user) {
       Long roleId= user.getRoleId();
       user.setRole(roleService.findById(roleId).getData().getName());
       Long unitId =user.getUnitId();
       user.setUnit(unitService.findById(unitId).getData().getName());
       user.setAccountType(1);
       user.setLocked(false);
       user.setUpdateAt(new Date());       user.setCreateAt(new Date());

        userService.insert(user);
        ModelAndView result = new ModelAndView("redirect:/users/manage");
        return result;
    }

    @GetMapping(value = "/add-user")
    public ModelAndView addUser() {
        UnitAndRoleAndAuthDto unitAndRoleAndAuthDto =new UnitAndRoleAndAuthDto();

        List<Role> roles = roleService.getAll().getData();
        List<Unit> units = unitService.selectAll().getData();
        List<RoleAuths> roleAuths = roleAuthService.getAll().getData();
        unitAndRoleAndAuthDto.setRoleAuths(roleAuths);
        unitAndRoleAndAuthDto.setRoles(roles);
        unitAndRoleAndAuthDto.setUnits(units);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unitAndRoleAndAuthDto",unitAndRoleAndAuthDto);
        modelAndView.setViewName("/userManage/add_user");
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

    @GetMapping(value="/manage")
    public ModelAndView userManage(){

        List<User> users = userService.getAll().getData();
        List<UserManageDto> userManageDtos = new ArrayList<UserManageDto>();
        for (User user: users){

            UserManageDto userManageDto =new UserManageDto();
            userManageDto.setId(user.getId().intValue());
            userManageDto.setUserName(user.getUserName());
            userManageDto.setRealName(user.getRealName());
            userManageDto.setUnitName(user.getUnit());
            userManageDto.setLocked(user.getLocked().toString());
            userManageDto.setRole(user.getRole());
            userManageDto.setUnitName(unitService.findById(user.getUnitId()).getData().getName());
            userManageDto.setAuth(roleService.findById(Long.valueOf(user.getAuth())).getData().getName());
            userManageDtos.add(userManageDto);
        }

        ModelAndView result = new ModelAndView();
        result.addObject("userManageDtos",userManageDtos);
        result.setViewName("/userManage/user_manage");
        return result;


    }



    @GetMapping(value = "/get-unit-role-auth")
    public ModelAndView getUnitRoleAuth(){

        UnitAndRoleAndAuthDto unitAndRoleAndAuthDto =new UnitAndRoleAndAuthDto();

        List<Role> roles = roleService.getAll().getData();
        List<Unit> units = unitService.selectAll().getData();
        List<RoleAuths> roleAuths = roleAuthService.getAll().getData();
        unitAndRoleAndAuthDto.setRoleAuths(roleAuths);
        unitAndRoleAndAuthDto.setRoles(roles);
        unitAndRoleAndAuthDto.setUnits(units);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("unitAndRoleAndAuthDto",unitAndRoleAndAuthDto);
        modelAndView.setViewName("/userManage/addUser");
        return  modelAndView;


    }



    @GetMapping(value = "/get-all-role-auth")
    public ModelAndView getAllRoleAuths(){

        List<RoleAuths> roleAuths = roleAuthService.getAll().getData();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roleAuths",roleAuths);
        modelAndView.setViewName("/roleManage/roleAuth");
        return  modelAndView;
    }
    @GetMapping(value = "/get-tables")
    public ModelAndView getTables(){

        List<Ledger> ledgers = ledgerService.selectAll().getData();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ledgers",ledgers);
        modelAndView.setViewName("/roleManage/select_auth");
        return  modelAndView;
    }

    @PostMapping(value = "/add-role")
    public ModelAndView addRoleAuths(String userName, HttpServletRequest httpServletRequest) {
       String[] ids= httpServletRequest.getParameterValues("ids");
        for (String id:ids){
            RoleAuths roleAuths =new RoleAuths();
            roleAuths.setAuth(userName);
            roleAuths.setId(Long.valueOf(id));
            roleAuths.setCreateAt(new Date());
            roleAuths.setUpdateAt(new Date());

            Role role = new Role();
            role.setName(userName);
            role.setCreateAt(new Date());
            role.setUpdateAt(new Date());
            Integer roleId = roleService.insert(role).getData();
            roleAuths.setRoleId(Long.valueOf(roleId));
            roleAuthService.insert(roleAuths);
        }
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("messsage","添加成功！");
        modelAndView.setViewName("/roleManage/roleAuth");
        return  modelAndView;
    }



}