package com.shop.service.impl;

import com.shop.dao.HbaseOperate;
import com.shop.entity.Torder;
import com.shop.message.OrderInfoReq;
import com.shop.message.PubResultSet;
import com.shop.message.StatusCode;
import com.shop.model.ShopOrderInfo;
import com.shop.service.IHbaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/9/3 10:17
 **/
public class HbaseServiceImpl implements IHbaseService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    HbaseOperate hbaseOperate;

    @Override
    public PubResultSet<ShopOrderInfo> queryOrderInfos(String beginDate, String endDate) {
        logger.error("*****************HbaseServiceImpl req "+ beginDate+" "+endDate);
        System.out.println("*****************HbaseServiceImpl req "+ beginDate+" "+endDate);
        OrderInfoReq req=new OrderInfoReq();
        req.setBeginDate(beginDate);
        req.setEndDate(endDate);
        logger.error("*************dao methed before*****************");
        System.out.println("*****************dao methed before********************");
        //异常处理
        List<Torder> torders = null;
        try {
            torders = hbaseOperate.find(req);
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.error("*****************dao methed after*****************");
        System.out.println("*****************dao methed after*****************");
        //出参
        PubResultSet<ShopOrderInfo> obdResultSet = new PubResultSet<>();
        List<ShopOrderInfo> data = new ArrayList<>();

        for (Torder torder : torders) {
            ShopOrderInfo shopOrderInfo = new ShopOrderInfo();
            BeanUtils.copyProperties(torder,shopOrderInfo);
            data.add(shopOrderInfo);
        }
        obdResultSet.setCode(StatusCode.SUCCESS.code());
        obdResultSet.setDesc(StatusCode.SUCCESS.desc());
        obdResultSet.setData(data);

        return obdResultSet;
    }

    @Override
    public void insertOrderInfo(Torder order) {
        try {
            hbaseOperate.put(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}