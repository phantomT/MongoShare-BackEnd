package com.ustc.config;

import com.ustc.entity.SessionUserBean;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截器
// 实现 HandlerInterceptor 接口的就是拦截器
// 此处拦截器的作用：防止未登录直接url访问登录成功之后的页面
public class LoginHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 登录成功之后，应该有用户的session。LoginController中登录成功会有setAttribute("loginUser", username);
        SessionUserBean loginUser = (SessionUserBean) request.getSession().getAttribute("loginUser");

        if (loginUser == null){
            // 没有登录成功
            request.setAttribute("msg","没有权限，请先登录");
            request.getRequestDispatcher("/index.html").forward(request, response);  // 跳回index.html，并输出msg
            return false;  // return false就是不放行，true是放行
        } else {
            return true;
        }

    }
}
