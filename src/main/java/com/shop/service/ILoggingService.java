package com.shop.service;

import com.shop.entity.LoggingInfoPO;
import com.shop.message.PubResultSet;
import com.shop.model.LoggingInfoBO;

/**
 * @author ChenTengfei
 * @date 2019/8/14 18:57
 **/
public interface ILoggingService {
    PubResultSet<LoggingInfoBO> queryLogInfos(String beginDate, String endDate);
    void insertLogInfo(LoggingInfoPO po);
}
