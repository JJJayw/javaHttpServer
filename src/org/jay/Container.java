package org.jay;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Container {

    private static final Map<String, Servlet> SERVLETS = new ConcurrentHashMap<>();

    // 启动的时候注册
    static {
        SERVLETS.put("/user", new UserServlet());
        SERVLETS.put("/login", new LoginServlet());
        SERVLETS.put("/register", new RegisterServlet());
    }

    public static Servlet getServlet(String uri) {
        return SERVLETS.get(uri);
    }
}
