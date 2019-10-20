package com.shop.cache;

/**
 * @author ChenTengfei
 * @date 2019/8/13 11:30
 **/
public interface ICacheService {
    /**
     * 保存用户登录信息到缓存服务器
     *
     * @param sessionId  当前会话ID
     * @param userModel  用户信息
     * @param timeToLive 有效时长（秒）
     * @return boolean
     */
    public abstract boolean saveUserToken(String sessionId, Object userModel, int timeToLive);

    /**
     * 获取用户缓存数据
     *
     * @param sessionId 当前会话ID
     * @return 用户信息
     */
    public abstract Object getUserTokenVal(String sessionId);

    /**
     * 删除用户缓存数据
     *
     * @param sessionId 当前会话ID
     */
    public abstract void removeCache(String sessionId);
}
