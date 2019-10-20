package com.shop.service;

import com.shop.entity.Torder;
import com.shop.message.PubResult;
import com.shop.message.PubResultSet;
import com.shop.model.ShopOrderInfo;

/**
 * @author ChenTengfei
 * @date 2019/9/3 10:14
 **/
public interface IHbaseService {
    public PubResultSet<ShopOrderInfo> queryOrderInfos(String beginDate, String endDate);
    public void insertOrderInfo(Torder order);
}
