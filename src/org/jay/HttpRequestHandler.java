package org.jay;

import java.io.IOException;
import java.io.InputStream;

public class HttpRequestHandler {
    public static HttpRequest getRequest(InputStream inputStream) {
        StringBuilder request = new StringBuilder();
        try {
            byte[] buf = new byte[1024];
            int len;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            do {
                len = inputStream.read(buf);
                request.append(new String(buf, 0, len));
            } while (inputStream.available() > 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        HttpRequest httpRequest = new HttpRequest();
        String[] headAndBody = request.toString().split("\r\n\r\n");
        //  处理实体
        if (headAndBody.length > 1 && headAndBody[1] != null) {
            httpRequest.setBody(headAndBody[1]);
        }
        //  处理请求行
        String[] startAndHeaders = headAndBody[0].split("\r\n");

        String[] startLineElements = startAndHeaders[0].split(" ");
        httpRequest.setMethod(startLineElements[0]);
        // 处理URI获得参数
        String[] uriAndParam = startLineElements[1].split("\\?");
        httpRequest.setUri(uriAndParam[0]);
        // 处理参数得到多个参数
        if (uriAndParam.length > 1 && uriAndParam[1] != null) {
            String[] params = uriAndParam[1].split("&");
            for (String s : params) {
                String[] param = s.split("=");
                httpRequest.setParameter(param[0], param[1]);
            }
        }
        httpRequest.setProtocol(startLineElements[2]);

        for (int i = 1; i < startAndHeaders.length; i++) {
            String header = startAndHeaders[i];
            String[] keyAndValue = header.split(": ");
            httpRequest.setHeader(keyAndValue[0], keyAndValue[1]);
        }
        return httpRequest;
    }

}
