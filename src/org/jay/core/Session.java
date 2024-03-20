package org.jay.core;

import java.util.HashMap;

public class Session extends HashMap<String, Object> {
    public void setAttribute(String key, Object object) {
        super.put(key, object);
    }

    public Object getAttribute(String key) {
        return super.get(key);
    }
}
