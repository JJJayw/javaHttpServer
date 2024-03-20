package org.jay.controller;

import org.jay.core.*;
import org.jay.dao.UserDao;
import org.jay.entity.User;

public class LoginServlet implements Servlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            HttpResponse.fail(response.getOutputStream(), "用户不存在");
        } else {
            // 用户存在密码相等
            if (user.getPassword() != null && user.getPassword().equals(password)) {
                HttpResponse.success(response.getOutputStream(), "登录成功");
                if (request.getHeader("Cookie") != null) {
                    // 从cookie中获取id
                    String cookie = request.getHeader("Cookie");
                    String sessionid = cookie.split("=")[1];
                    Session session = Container.getSession(sessionid);
                    session.setAttribute("user", user);
                }
            } else {
                HttpResponse.fail(response.getOutputStream(), "密码错误");
            }
        }
    }
}
