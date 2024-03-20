package org.jay.controller;

import org.jay.core.*;
import org.jay.entity.User;

public class IndexServlet implements Servlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        if (request.getHeader("Cookie") != null) {
            // 从cookie中获取id
            String cookie = request.getHeader("Cookie");
            String sessionid = cookie.split("=")[1];
            Session session = Container.getSession(sessionid);
            if (session == null || session.get("user") == null) {
                HttpResponse.redirect(response.getOutputStream(), "http://127.0.0.1:8888/login.html");
                return;
            }
            User user = (User) session.get("user");
            HttpResponse.success(response.getOutputStream(), "当前用户：" + user.getUsername());
        } else {
            // 重定向到登录页面
            HttpResponse.redirect(response.getOutputStream(), "http://127.0.0.1:8888/login.html");
        }
    }
}
