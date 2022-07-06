package com.bknife.web;

import java.util.Collection;

public interface Session {

    /**
     * 获取sessionId
     * 
     * @return
     */
    public String getSessionId();

    /**
     * 获取所有属性名称
     * 
     * @return
     */
    public Collection<Object> getAttributeNames();

    /**
     * 获取绑定的session对象
     * 
     * @param <T>
     * @param clazz
     * @return
     */
    public <T> T getSessionUser(Class<T> clazz);

    /**
     * 设置绑定的对象
     * 
     * @param <T>
     * @param object
     */
    public <T> void setSessionUser(T object);

    /**
     * 设置属性
     * 
     * @param key
     * @param value
     */
    public void setAttribute(Object key, Object value);

    /**
     * 删除属性
     * 
     * @param key
     */
    public void removeAttribute(Object key);

    /**
     * 获取属性对象
     * 
     * @param key
     * @return
     */
    public Object getAttribute(Object key);

    /**
     * 获取指定类型属性对象,对象将自动转换为clazz类型
     * 
     * @param key
     * @return
     */
    public <T> T getAttribute(Object key, Class<T> clazz);

    /**
     * 获取指定类型属性对象,对象将自动转换为clazz类型
     * 
     * @param key
     * @param defaulValue 默认值
     * @return
     */
    public <T> T getAttribute(Object key, Class<T> clazz, T defaultValue);

    /**
     * 获取int类型属性
     * 
     * @param key
     * @return
     */
    public int getAttributeInt(Object key);

    /**
     * 获取int类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public int getAttributeInt(Object key, int defaultValue);

    /**
     * 获取long类型属性
     * 
     * @param key
     * @return
     */
    public long getAttributeLong(Object key);

    /**
     * 获取long类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public long getAttributeLong(Object key, long defaultValue);

    /**
     * 获取double类型属性
     * 
     * @param key
     * @return
     */
    public double getAttributeDouble(Object key);

    /**
     * 获取double类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public double getAttributeDouble(Object key, double defaultValue);

    /**
     * 获取String类型属性
     * 
     * @param key
     * @return
     */
    public String getAttributeString(Object key);

    /**
     * 获取String类型属性
     * 
     * @param key
     * @param defaultValue
     * @return
     */
    public String getAttributeString(Object key, String defaultValue);
}
