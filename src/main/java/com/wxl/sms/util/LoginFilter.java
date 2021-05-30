package com.wxl.sms.util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 完成登录验证的过滤器
 *
 * @author wxl on 2021/5/8 23:21
 */
//@Component
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;

        // 1.获取资源请求路径
        String requestURI = request.getRequestURI();
        // 2.判断是否包含登录相关资源路径
        if (requestURI.contains("/login") || requestURI.contains("login.jsp")) {
            // 证明用户就是想要登录
            chain.doFilter(request, res);
        } else {
            // 需要验证用户是否登录 -> 从session中获取User
            Object user = request.getSession().getAttribute("user");
            if (user != null) {
                chain.doFilter(request, res);
            } else {
                // 没有登录, 跳转登录页面
                request.setAttribute("LOGIN_MSG", "您尚未登录, 请登录");
                request.getRequestDispatcher("/login.jsp").forward(request, res);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
