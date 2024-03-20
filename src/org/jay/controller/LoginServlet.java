package org.jay.controller;

import org.jay.core.HttpRequest;
import org.jay.core.HttpResponse;
import org.jay.core.Servlet;
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
            HttpResponse.fail(response.getOutputStream(),"用户不存在");
        }else {
            // 用户存在密码相等
            if (user.getPassword() != null && user.getPassword().equals(password)) {
                HttpResponse.success(response.getOutputStream(),"登录成功");
            }else {
                HttpResponse.fail(response.getOutputStream(),"密码错误");
            }
        }
    }
}
