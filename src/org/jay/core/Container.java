package org.jay.core;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// 注册Servlet到容器中
public class Container {

    private static final ServletContainer SERVLETS = new ServletContainer();

    private static final Map<String, Session> SESSIONS = new ConcurrentHashMap<>();

    public static Servlet getServlet(String uri) throws ClassNotFoundException {
        return SERVLETS.get(uri);
    }

    public static void addSession(String key, Session session) {
        SESSIONS.put(key, session);
    }

    public static Session getSession(String key) {
        return SESSIONS.get(key);
    }
}
