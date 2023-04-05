package com.buba.controller.login;

import com.buba.pojo.User;
import com.buba.service.login.LoginService;
import com.buba.service.login.LoginServiceImpl;
import com.buba.tool.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * @author chenrui
 * @version 1.0
 * @description: TODO
 * @date 2023/4/5 13:44
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    /**
     * 登录
     * @param model
     * @param session
     * @param userCode
     * @param userPassword
     * @return
     */
    @RequestMapping
    public String login(Model model, HttpSession session, String userCode, String userPassword) {
        LoginService loginService = new LoginServiceImpl();
        User user = loginService.login(userCode, userPassword);

        String view = "";
        if (user == null) {
            // 用户不存在
            model.addAttribute("error", "该用户不存在！");
            view = "../login";
        } else {
            if (user.getUserPassword().equals(userPassword)) {
                // 登录成功
                //放入session
                session.setAttribute(Constants.USER_SESSION, user);
                //页面跳转（frame.jsp）
                view = "frame";
            } else {
                // 密码错误
                model.addAttribute("error", "密码错误！");
                view = "../login";
            }
        }
        return view;
    }

    /**
     * 注销
     * @param session
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(Constants.USER_SESSION);
        return "../login";
    }
}
