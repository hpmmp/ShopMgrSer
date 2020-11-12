package com.shop.service.impl;

import com.shop.dao.HiveOperate;
import com.shop.entity.LoggingInfoPO;
import com.shop.message.OrderInfoReq;
import com.shop.message.PubResultSet;
import com.shop.message.StatusCode;
import com.shop.model.LoggingInfoBO;
import com.shop.service.ILoggingService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/8/14 19:21
 **/

public class LoggingServiceImpl implements ILoggingService {

    @Autowired
    HiveOperate hiveOperate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PubResultSet<LoggingInfoBO> queryLogInfos(String beginDate, String endDate) {

        logger.info("into LoggingServiceImpl--queryLogInfos");
        logger.info("beginDate:"+beginDate+"  "+"endDate:"+endDate);
        OrderInfoReq req=new OrderInfoReq();
        req.setBeginDate(beginDate);
        req.setEndDate(endDate);
        List<LoggingInfoPO> loggingInfoPOList = hiveOperate.queryLogInfos(req);

        for (LoggingInfoPO loggingInfoPO : loggingInfoPOList) {
            logger.info("loggingInfoPO"+loggingInfoPO.toString());
        }

        PubResultSet<LoggingInfoBO> obdResultSet = new PubResultSet<>();
        //将POList组装到BOList

        List<LoggingInfoBO> data = packList(loggingInfoPOList);

        obdResultSet.setCode(StatusCode.SUCCESS.code());
        obdResultSet.setDesc(StatusCode.SUCCESS.desc());
        obdResultSet.setData(data);

        return obdResultSet;
    }
    public List<LoggingInfoBO> packList(List<LoggingInfoPO> loggingInfoPOList){
        List<LoggingInfoBO> data = new ArrayList<LoggingInfoBO>();
        if (null != loggingInfoPOList && 0 < loggingInfoPOList.size()) {
            for (LoggingInfoPO loggingInfoPO : loggingInfoPOList) {
                LoggingInfoBO loggingInfoBO = new LoggingInfoBO();
                BeanUtils.copyProperties(loggingInfoPO,loggingInfoBO);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    loggingInfoBO.setTime(sdf.parse(loggingInfoPO.getTime()));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                data.add(loggingInfoBO);
            }
        }
        return data;
    }
    
    /**
     * 插入一条日志信息
     */
	@Override
	public void insertLogInfo(LoggingInfoPO po) {
		hiveOperate.insertLogInfo(po);
	}
}