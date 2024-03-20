package org.jay;

import org.dom4j.DocumentException;
import org.jay.core.*;
import org.jay.util.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

public class WebServer {
    public static void main(String[] args) throws IOException, DocumentException, ClassNotFoundException {
        // 创建一个服务器监听在8888端口
        ServerSocket serverSocket = new ServerSocket(Configuration.PORT);
        System.out.println("服务已经监听在" + Configuration.PORT + "端口");
        while (true) {
            Socket accept = serverSocket.accept();
            // 获取socket的输入流和输出流
            InputStream inputStream = accept.getInputStream();
            OutputStream outputStream = accept.getOutputStream();

            HttpRequest request = HttpRequestHandler.getRequest(inputStream);
            HttpResponse response = new HttpResponse();
            response.setOutputStream(outputStream);

            String uri = request.getUri();

            System.out.println(request.getUri() + " " + request.getMethod());

            // 统一处理请求


            // 处理cookie
            // 服务端让浏览器写cookie

            if (request.getHeader("Cookie") == null) {
                createSession(response);
            } else {
                String cookie = request.getHeader("Cookie");
                if (!cookie.contains("jsessionid")) {
                    createSession(response);
                } else {
                    String jsessionid = cookie.split("=")[1];
                    if (Container.getSession(jsessionid) == null) {
                        createSession(response);
                    }
                }
            }

            // 静态资源
            if (uri.contains(".html")) {
                // 拼接文件地址字符串
                String path = Configuration.BASE_URL + request.getUri();
                // 处理HTML
                String body = IOUtils.getContext(path);
                // 按照http协议的格式封装一个报文
                if (body != null) {
                    response.setHeader("Content-Type", "text/html;charset=UTF-8");
                    response.setHeader("Content-Length", Integer.toString(body.getBytes().length));
                    response.setBody(body);
                    // 将报文写出给浏览器
                    HttpResponseHandler.write(outputStream, response);
                }
//                处理动态资源
            } else if (uri.contains(".do")) {
                Servlet servlet = Container.getServlet(request.getUri());
                if (servlet != null) {
                    servlet.service(request, response);
                }
            }

//            统一处理响应

            outputStream.close();
            inputStream.close();
            accept.close();
        }
    }

    private static void createSession(HttpResponse response) {
        String jsessionid = UUID.randomUUID().toString();
        response.setHeader("set-Cookie", "jsessionid=" + jsessionid);
        Container.addSession(jsessionid, new Session());
    }
}
