package com.tzxt.interceptor;

import com.tzxt.exception.RestException;
import com.tzxt.util.SecurityHelper;
import org.assertj.core.util.Lists;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 权限 拦截器
 * 1. 是否登录
 * 2. 是否有查询 某台账信息 的数据权限
 * Created by wangshang on 17/5/26.
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
    private List<String> noAuthUrl = Lists.newArrayList(
            "/",
            "/login",
            "/ordinaryLogin",
            "/register",
            "/logout",
            "/validate",
            "/revalidate",
            "/lock"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (noAuthUrl.stream().anyMatch(url -> url.equals(requestURI)))
            return true;

        if (!SecurityHelper.isLoginUser()) {
            throw new RestException(HttpStatus.FORBIDDEN, "用户未登录");
        }
        return true;
    }

}
