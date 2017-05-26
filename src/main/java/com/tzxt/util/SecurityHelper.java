package com.tzxt.util;

import com.tzxt.exception.RestException;
import com.tzxt.model.RoleAuths;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * 安全助手
 * <p>
 * Created by wangshang on 17/5/26.
 */
public class SecurityHelper {

    /**
     * 检测 用户是否登录
     *
     * @return
     */
    public static Boolean isLoginUser() {
        return CurrentUser.get() != null ? Boolean.TRUE : Boolean.FALSE;
    }

    /**
     * 检测 用户的 数据权限  普通用户
     */
    public static void checkPermission(Long ledgerId, HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null)
            throw new RestException(HttpStatus.FORBIDDEN, "您还未登陆，请登录");
        List<RoleAuths> auths = JsonMapper.JSON_NON_EMPTY_MAPPER.fromJson(session.getAttribute(Constants.SESSION_AUTHS).toString(),
                JsonMapper.JSON_NON_EMPTY_MAPPER.createCollectionType(List.class, RoleAuths.class));
        Optional<RoleAuths> optional = auths.stream().filter(ra -> Objects.equals(ra.getLedgerId(), ledgerId)).findFirst();
        if (!optional.isPresent())
            throw new RestException(HttpStatus.FORBIDDEN, "抱歉您没有访问此台账表的权限");
    }

    /**
     * 检测 用户角色 是不是 系统管理员
     */
    public static void chechAdmin() {
        if (!Objects.equals(AccountType.ADMIN.getValue(), CurrentUser.get().getAccountType()))
            throw new RestException(HttpStatus.FORBIDDEN, "您不是系统管理员，不能进行此类操作！");
    }
}
