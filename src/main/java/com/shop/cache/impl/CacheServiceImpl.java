package com.shop.cache.impl;

import com.shop.cache.ICacheService;
import com.shop.cache.RedisCacheAdaptor;
import com.shop.entity.sys.SysUser;
import com.shop.utils.StringHelper;
 
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author ChenTengfei
 * @date 2019/8/13 11:40
 **/
@Service
public class CacheServiceImpl implements ICacheService {
    private Logger logger = LoggerFactory.getLogger(CacheServiceImpl.class);

    @Autowired
    private RedisCacheAdaptor redisCacheAdaptor;

    @Override
    public boolean saveUserToken(String sessionId, Object userModel, int timeToLive) {
        if(StringHelper.isBlank(sessionId)||userModel==null){
            logger.info("sessionId and userModel cannot be null!");
            return false;
        }else{
            redisCacheAdaptor.put(sessionId,userModel.toString(),timeToLive);
            return true;
        }
    }

    @Override
    public Object getUserTokenVal(String sessionId) {
        if(StringHelper.isBlank(sessionId)){
            logger.info("sessionId cannot be null!");
            return null;
        }else{
            return redisCacheAdaptor.get(sessionId);
        }
    }

    @Override
    public void removeCache(String sessionId) {
        if (StringHelper.isBlank(sessionId)) {
            logger.info("sessionId cannot be null!");
        } else {
            redisCacheAdaptor.delete(sessionId);
            logger.info("remove {} cache success!", sessionId);
        }
    }
}