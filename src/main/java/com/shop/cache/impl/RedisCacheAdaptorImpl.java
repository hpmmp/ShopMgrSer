package com.shop.cache.impl;

import com.shop.cache.RedisCacheAdaptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author ChenTengfei
 * @date 2019/8/13 10:42
 **/
@Service
public class RedisCacheAdaptorImpl implements RedisCacheAdaptor{

    private static Logger logger = LoggerFactory.getLogger(RedisCacheAdaptorImpl.class);

    @Autowired
    private JedisPool jedisPool;

    private Jedis jedis;


    @Override
    public void put(String key, String value, int timeToLive) {
        jedis = jedisPool.getResource();
        logger.info("正在插入数据："+key+"--"+value);
        //jedis.set(key,value,"nx","ex",timeToLive);
        jedis.set(key,value);
        jedis.expire(key,timeToLive);
        jedis.close();
    }

    @Override
    public String get(String key) {
        jedis = jedisPool.getResource();
        String result = jedis.get(key);
        jedis.close();
        return result;
    }

    @Override
    public void delete(String key) {
        jedis = jedisPool.getResource();
        logger.info("正在删除key："+key);
        jedis.del(key);
        jedis.close();
    }
}