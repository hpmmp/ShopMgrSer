package com.shop.service.impl;

import com.shop.dao.HbaseOperate;
import com.shop.dao.TstatisticsMapper;
import com.shop.entity.Torder;
import com.shop.entity.Tstatistics;
import com.shop.message.OrderInfoReq;
import com.shop.message.PubResultSet;
import com.shop.message.StatusCode;
import com.shop.model.ShopOrderInfo;
import com.shop.service.IStatisticsSerivce;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/9/5 16:43
 **/
public class StatisticsSerivceImpl implements IStatisticsSerivce {

    @Autowired
    private TstatisticsMapper tstatisticsMapper;
    @Autowired
    HbaseOperate hbaseOperate;
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public PubResultSet<Tstatistics> queryStatisticsInfos() {
        logger.error("StatisticsSerivceImpl-queryStatisticsInfos");
        System.out.println("StatisticsSerivceImpl-queryStatisticsInfos");
        List<Tstatistics> tstatistics = tstatisticsMapper.queryStatisticsInfos();
        PubResultSet<Tstatistics> obdResultSet = new PubResultSet<>();


        obdResultSet.setCode(StatusCode.SUCCESS.code());
        obdResultSet.setDesc(StatusCode.SUCCESS.desc());
        obdResultSet.setData(tstatistics);

        return obdResultSet;
    }
}