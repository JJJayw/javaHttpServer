package org.jay;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest implements Serializable {


    private static final long serialVersionUID = 1L;
    // 协议
    private String protocol;
    // 请求方式
    private String type;
    // uri
    private String uri;
    // 请求头
    private Map<String, String> header = new HashMap<>(16);
    // 请求体
    private String body;
    // 请求参数
    private Map<String, String> parameter = new HashMap<>(8);

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    // 请求方法
    private String method;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getHeader(String key) {
        return this.header.get(key);
    }

    public void setHeader(String key, String value) {
        this.header.put(key, value);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void addHeader(String key, String value) {
        header.put(key, value);
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getParameter(String name) {
        return parameter.get(name);
    }

    public void setParameter(String name, String value) {
        this.parameter.put(name, value);
    }

    @Override
    public String toString() {
        return "HttpRequest{" +
                "protocol='" + protocol + '\'' +
                ", type='" + type + '\'' +
                ", uri='" + uri + '\'' +
                ", header=" + header +
                ", body='" + body + '\'' + "\n" +
                ", parameter=" + parameter + "\n" +
                ", method='" + method + '\'' +
                '}';
    }
}
