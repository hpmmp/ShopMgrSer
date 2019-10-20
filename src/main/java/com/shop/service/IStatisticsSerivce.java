package com.shop.service;

import com.shop.entity.Tstatistics;
import com.shop.message.PubResultSet;

/**
 * @author ChenTengfei
 * @date 2019/9/5 16:41
 **/
public interface IStatisticsSerivce {
    public PubResultSet<Tstatistics> queryStatisticsInfos();
}
