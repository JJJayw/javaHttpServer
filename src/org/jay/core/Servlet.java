package org.jay.core;

public interface Servlet {
    /*
    * 根据请求，处理响应*/
    void service(HttpRequest request, HttpResponse response);

}
