package com.shop.cache;

/**
 * @author ChenTengfei
 * @date 2019/8/13 10:41
 **/
public interface RedisCacheAdaptor {

    public abstract void put(String key, String value, int timeToLive);

    public abstract String get(String key);

    public abstract void delete(String key);
}