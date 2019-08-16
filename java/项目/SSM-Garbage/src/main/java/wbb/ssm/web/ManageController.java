package wbb.ssm.web;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wbb.ssm.entity.Manage;
import wbb.ssm.service.ManageService;

@RestController
public class ManageController {
    @Autowired
    private ManageService manageService;

    @RequestMapping({"/manageLogin"})
    public void manageLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("utf-8");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String sessionId = request.getRequestedSessionId();
        Cookie cookie = new Cookie("sessionId", sessionId);
        response.addCookie(cookie);
        cookie.setMaxAge(60);
        request.getSession().setAttribute("success", true);
        request.getSession().setMaxInactiveInterval(60);
        response.getWriter().write(manageService.manageLogin(username, password));
    }

    @RequestMapping({"/queryManege"})
    public Manage queryManege(@RequestParam String username) {
        return manageService.queryManage(username);
    }
}
