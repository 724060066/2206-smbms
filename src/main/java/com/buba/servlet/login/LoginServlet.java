package com.buba.servlet.login;

import com.buba.pojo.User;
import com.buba.service.login.LoginService;
import com.buba.service.login.LoginServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author chenrui
 * @version 1.0
 * @date 2023/3/23 13:39
 */
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method != null && "login".equals(method)) {
            // 登录
            this.login(req, resp);
        } else {
            // 注销
            this.loginOut(req, resp);
        }
    }

    /**
     * 登录
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userCode = req.getParameter("userCode");
        String userPassword = req.getParameter("userPassword");

        LoginService loginService = new LoginServiceImpl();
        User user = loginService.login(userCode, userPassword);

        if (user == null) {
            // 用户不存在
            req.setAttribute("error", "该用户不存在！");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        } else {
            if (user.getUserPassword().equals(userPassword)) {
                // 登录成功
                //放入session
                req.getSession().setAttribute("userSession", user);
                //页面跳转（frame.jsp）
                resp.sendRedirect("jsp/frame.jsp");
            } else {
                // 密码错误
                req.setAttribute("error", "密码错误！");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        }
    }

    /**
     * 注销
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    private void loginOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //放入session
        req.getSession().removeAttribute("userSession");
        //页面跳转（frame.jsp）
        resp.sendRedirect("login.jsp");
    }
}
