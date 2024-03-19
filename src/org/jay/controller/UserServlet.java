package org.jay.controller;

import org.jay.core.HttpRequest;
import org.jay.core.HttpResponse;
import org.jay.core.HttpResponseHandler;
import org.jay.core.Servlet;
import org.jay.dao.UserDao;
import org.jay.entity.User;

public class UserServlet implements Servlet {

    @Override
    public void service(HttpRequest request, HttpResponse response) {
        int id = Integer.parseInt(request.getParameter("id"));
        UserDao userDao = new UserDao();
        User user = userDao.findUserById(id);
        if (user != null) {
            response.setHeader("Content-Type", "text/plain;charset=UTF-8");
            response.setHeader("Content-Length", Integer.toString(user.toString().getBytes().length));
            response.setBody(user.toString());
            // 将报文写出给浏览器
            HttpResponseHandler.write(response.getOutputStream(), response);
        }
    }
}
