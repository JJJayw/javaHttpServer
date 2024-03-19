package org.jay.core;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.Map;

public class HttpResponseHandler implements Serializable {
    // 将响应对象序列化成字符串报文
    public static String build(HttpResponse response) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(response.getProtocol()).append(" ")
                .append(response.getCode()).append(" ")
                .append(response.getMessage()).append("\r\n");
        // 拼接首部信息
        for (Map.Entry<String, String> entry : response.getHeaders().entrySet()) {
            stringBuilder.append(entry.getKey()).append(": ").append(entry.getValue()).append("\r\n");
        }
        stringBuilder.append("\r\n");

        if (response.getBody() != null) {
            stringBuilder.append("\r\n").append(response.getBody());
        }
        return stringBuilder.toString();
    }

    public static void write(OutputStream outputStream, HttpResponse httpResponse) {
        String message = build(httpResponse);
        try {
            outputStream.write(message.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.setCode("302");
        httpResponse.setMessage("Moved Temporarily");
        httpResponse.setHeader("Location", "https://www.baidu.com");
        System.out.println(HttpResponseHandler.build(httpResponse));
    }
}
