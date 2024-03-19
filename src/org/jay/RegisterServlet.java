package org.jay;

public class RegisterServlet implements Servlet {
    @Override
    public void service(HttpRequest request, HttpResponse response) {
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            UserDao userDao = new UserDao();
            User user = userDao.findUserByUsername(username);
            if (user == null) {
                userDao.insertUser(new User(2, username, password));
                HttpResponse.success(response.getOutputStream(), "注册成功");
            } else {
                // 用户已经存在
                HttpResponse.fail(response.getOutputStream(), "注册失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            HttpResponse.error(response.getOutputStream(), "有异常发生");
        }
    }
}
