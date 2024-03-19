package org.jay;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class WebServer {
    public static void main(String[] args) throws IOException {
        int port = 8888;
        // 创建一个服务器监听在8888端口
        ServerSocket serverSocket = new ServerSocket(port);
        System.out.println("服务已经监听在" + port + "端口");
        while (true) {
            Socket accept = serverSocket.accept();
            InputStream inputStream = accept.getInputStream();
            HttpRequest request = HttpRequestHandler.getRequest(inputStream);
            // 调试语句
//            System.out.println(request);

            // 拼接文件地址字符串
            String path = Constant.ROOT_DIR + request.getUri();

            OutputStream outputStream = accept.getOutputStream();
            // 按照http协议的格式封装一个报文

            HttpResponse response = new HttpResponse();
            if (request.getUri().contains("/user")) {
                int id = Integer.parseInt(request.getParameter("id"));
                UserDao userDao = new UserDao();
                User user = userDao.findUserById(id);
                if (user != null) {
                    response.setHeader("Content-Type", "text/plain;charset=UTF-8");
                    response.setHeader("Content-Length", Integer.toString(user.toString().getBytes().length));
                    response.setBody(user.toString());
                    // 将报文写出给浏览器
                    HttpResponseHandler.write(outputStream, response);
                }
            } else {
                response.setCode("404");
            }

//            处理HTML
//            String body = IOUtils.getContext(path);
//            if (body != null) {
//                response.setHeader("Content-Type", "text/html;charset=UTF-8");
//                response.setHeader("Content-Length", Integer.toString(body.getBytes().length));
//                response.setBody(body);
//                // 将报文写出给浏览器
//                HttpResponseHandler.write(outputStream, response);
//            }
            outputStream.close();
            inputStream.close();
            accept.close();
        }


    }
}
