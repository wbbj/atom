package wbb.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class IsLogin implements HandlerInterceptor {
    public IsLogin() {
    }

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //获取登录信息
        HttpSession session = httpServletRequest.getSession();
        //判断session是否含有success
        if (session.getAttribute("success") != null) {
            return true;//有success放通,跳转index.html
        } else {//没有不放通,拦截到login.html
            httpServletRequest.getRequestDispatcher("/login.html").forward(httpServletRequest, httpServletResponse);
            return false;
        }
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
