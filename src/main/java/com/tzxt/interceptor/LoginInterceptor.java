package com.tzxt.interceptor;

import com.tzxt.model.User;
import com.tzxt.service.UserService;
import com.tzxt.util.Constants;
import com.tzxt.util.CurrentUser;
import com.tzxt.util.ResponseHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录拦截器，设置当前用户信息
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(LoginInterceptor.class);

    private UserService userService;

    @Autowired
    public LoginInterceptor(UserService userService) {
        this.userService = userService;
    }

    /**
     * 请求之前 设置当前用户的信息
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        HttpSession session = request.getSession(false);
        if (session != null) {
            Object attribute = session.getAttribute(Constants.SESSION_USER_ID);
            if (attribute != null) {
                logger.info("init current user info.");
                Long userId = Long.parseLong(String.valueOf(attribute));
                User currentUser = ResponseHelper.getOrThrow(userService.getById(userId));
                CurrentUser.set(currentUser);
            }
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        CurrentUser.clear();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }

}