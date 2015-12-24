package com.uncodeframework.core.plugins.easyxls.bean;

import java.util.Map;

public class MapField {
    private String name;

    public MapField(String name) {
        this.name = name;
    }

    public void set(Object object, Object value) {
        if (object != null && object instanceof Map) {
            ((Map) object).put(name, value);
        }
    }

    public Object get(Object object) {
        if (object != null && object instanceof Map) {
            return ((Map) object).get(name);
        }
        return null;
    }
}