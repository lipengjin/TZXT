package com.tzxt.controller;

import com.tzxt.dto.NewRoleModel;
import com.tzxt.dto.RoleModel;
import com.tzxt.model.Ledger;
import com.tzxt.model.Role;
import com.tzxt.model.RoleAuths;
import com.tzxt.service.LedgerService;
import com.tzxt.service.RoleAuthService;
import com.tzxt.service.RoleService;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.Response;
import com.tzxt.util.ResponseHelper;
import com.tzxt.util.SecurityHelper;
import org.assertj.core.util.Lists;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * 角色管理
 * <p>
 * Created by wangshang on 17/5/26.
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;
    private final RoleAuthService roleAuthService;
    private final LedgerService ledgerService;

    public RoleController(RoleService roleService,
                          RoleAuthService roleAuthService,
                          LedgerService ledgerService) {
        this.roleService = roleService;
        this.roleAuthService = roleAuthService;
        this.ledgerService = ledgerService;
    }

    @GetMapping("/addRole")
    public ModelAndView addRole() {
        // 1. 权限检测
        SecurityHelper.chechAdmin();
        // 2. 查询 台账表 列表
        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        ModelAndView result = new ModelAndView("/roleManage/add_role");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("ledgers", ledgers);
        return result;
    }

    @Transactional
    @PostMapping("/saveRole")
    public String saveRole(NewRoleModel newRoleModel) {
        SecurityHelper.chechAdmin();

        Role role = role(newRoleModel);
        ResponseHelper.getOrThrow(roleService.insert(role));
        ResponseHelper.getOrThrow(roleAuthService.insertAll(roleAuths(role.getId(), newRoleModel)));

        return "redirect:/role";
    }

    private List<RoleAuths> roleAuths(Long roleId, NewRoleModel newRoleModel) {
        List<RoleAuths> roleAuths = Lists.newArrayList();

        newRoleModel.getLedgerIds().forEach(legerId -> {
            RoleAuths ra = new RoleAuths();

            ra.setRoleId(roleId);
            ra.setAuthType(1);
            ra.setLedgerId(legerId);
            ra.setAuth(ResponseHelper.getOrThrow(ledgerService.getById(legerId)).getName());
            ra.setUpdateAt(new Date());
            ra.setCreateAt(new Date());
            roleAuths.add(ra);
        });
        return roleAuths;
    }

    private Role role(NewRoleModel newRoleModel) {
        Role role = new Role();
        role.setUpdateAt(new Date());
        role.setCreateAt(new Date());
        role.setName(newRoleModel.getRoleName());
        return role;
    }

    @GetMapping
    public ModelAndView roleManage() {

        // 1. 权限检测
        SecurityHelper.chechAdmin();
        // 2. 查询
        List<RoleModel> roleModels = Lists.newArrayList();
        List<Role> roles = ResponseHelper.getOrThrow(roleService.getAll());
        roles.forEach(role -> {
            RoleModel roleModel = new RoleModel();

            roleModel.setRole(role);
            List<RoleAuths> roleAuths = ResponseHelper.getOrThrow(roleAuthService.selectByRoleId(role.getId()));
            roleModel.setAuths(auths(roleAuths));
            roleModels.add(roleModel);
        });

        ModelAndView result = new ModelAndView("/roleManage/role_manage");

        result.addObject("currUser", CurrentUser.get());
        result.addObject("roleModels", roleModels);
        return result;
    }

    private String auths(List<RoleAuths> roleAuths) {

        if (roleAuths.size() <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
//        Optional<RoleAuths> operation = roleAuths.stream().filter(r -> 0 == r.getAuthType()).findFirst();
//        operation.ifPresent(roleAuths1 -> sb.append(roleAuths1.getAuth()).append(" | "));
        roleAuths.forEach(ra -> {
//            if (0 != ra.getAuthType())
            sb.append(ra.getAuth()).append(" | ");
        });

        return sb.toString().substring(0, sb.length() - 3);
    }

    /**
     * @return
     */
    @GetMapping("/edit/{roleId}")
    public ModelAndView editRole(@PathVariable Long roleId) {
        SecurityHelper.chechAdmin();

        List<Ledger> ledgers = ResponseHelper.getOrThrow(ledgerService.selectAll());
        ModelAndView result = new ModelAndView("/roleManage/edit_role");
        result.addObject("currUser", CurrentUser.get());
        result.addObject("role", ResponseHelper.getOrThrow(roleService.findById(roleId)));
        result.addObject("ledgers", ledgers);
        return result;
    }

    /**
     * 保存 编辑的 角色
     *
     * @param newRoleModel
     * @return
     */
    @Transactional
    @PostMapping("/saveEditRole")
    public String saveEditRole(NewRoleModel newRoleModel) {
        SecurityHelper.chechAdmin();

        ResponseHelper.getOrThrow(roleAuthService.deleteByRoleId(newRoleModel.getRoleId()));
        ResponseHelper.getOrThrow(roleAuthService.insertAll(roleAuths(newRoleModel.getRoleId(), newRoleModel)));

        return "redirect:/role";
    }

    @Transactional
    @GetMapping("/delete/{roleId}")
    public String deleteRole(@PathVariable Long roleId) {
        SecurityHelper.chechAdmin();

        ResponseHelper.getOrThrow(roleAuthService.deleteByRoleId(roleId));
        ResponseHelper.getOrThrow(roleService.deleteById(roleId));

        return "redirect:/role";
    }
}
