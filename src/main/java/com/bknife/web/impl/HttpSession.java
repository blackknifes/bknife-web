package com.bknife.web.impl;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.bknife.base.converter.ConverterUtils;
import com.bknife.web.Session;
import com.bknife.web.Sessions;

public class HttpSession implements Session {
    private String sessionId = Sessions.newSessionId();
    private Map<Object, Object> attributes = new HashMap<Object, Object>();
    private Object object;

    @Override
    public String getSessionId() {
        return sessionId;
    }

    @Override
    public synchronized Collection<Object> getAttributeNames() {
        return attributes.keySet();
    }

    @Override
    public synchronized <T> void setSessionUser(T object) {
        this.object = object;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getSessionUser(Class<T> clazz) {
        if (object == null)
            return null;
        if (clazz.isAssignableFrom(object.getClass()))
            return (T) object;
        return null;
    }

    @Override
    public synchronized void setAttribute(Object key, Object value) {
        attributes.put(key, value);
    }

    @Override
    public synchronized void removeAttribute(Object key) {
        attributes.remove(key);
    }

    @Override
    public synchronized Object getAttribute(Object key) {
        return attributes.get(key);
    }

    @Override
    public <T> T getAttribute(Object key, Class<T> clazz) {
        return getAttribute(key, clazz, null);
    }

    @Override
    public <T> T getAttribute(Object key, Class<T> clazz, T defaultValue) {
        Object val = getAttribute(key);
        if (val == null)
            return defaultValue;
        T tmp = ConverterUtils.convert(val, clazz);
        return tmp == null ? defaultValue : tmp;
    }

    @Override
    public int getAttributeInt(Object key) {
        return getAttributeInt(key, 0);
    }

    @Override
    public int getAttributeInt(Object key, int defaultValue) {
        return getAttribute(key, Integer.class, 0);
    }

    @Override
    public long getAttributeLong(Object key) {
        return getAttributeLong(key, 0);
    }

    @Override
    public long getAttributeLong(Object key, long defaultValue) {
        return getAttribute(key, Long.class, 0l);
    }

    @Override
    public double getAttributeDouble(Object key) {
        return getAttributeDouble(key, 0);
    }

    @Override
    public double getAttributeDouble(Object key, double defaultValue) {
        return getAttribute(key, Double.class, 0.0);
    }

    @Override
    public String getAttributeString(Object key) {
        return getAttributeString(key, "");
    }

    @Override
    public String getAttributeString(Object key, String defaultValue) {
        return getAttribute(key, String.class, "");
    }
}
