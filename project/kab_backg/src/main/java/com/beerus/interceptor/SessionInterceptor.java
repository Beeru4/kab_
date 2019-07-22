package com.beerus.interceptor;

import com.beerus.utils.Util;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author Beerus
 * @Description 会话拦截器 用来判断是否锁定账户或者怕断用户是否登入
 * @Date 2019-07-16
 **/
@Component
public class SessionInterceptor implements HandlerInterceptor {
    /**
     * 实现执行前拦截方法
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        // 判断用户是否登入过 没有登入过重定向到登入页面
        if (Util.isEmpty(session.getAttribute("admin"))) {
            response.sendRedirect("/kab_backg/");
            return false;
        } else if (null != session.getAttribute("status") && (int) session.getAttribute("status") == -1){
            // 拦截已登入用户
            response.sendRedirect("/kab_backg/lock_screen");
            return false;
        }
        return true;
    }
}
