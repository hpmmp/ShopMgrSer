package com.shop.service;

import com.shop.entity.Torder;
import com.shop.message.PubResult;
import com.shop.message.PubResultSet;
import com.shop.model.ShopOrderInfo;

public interface IShopOrder {
	public PubResultSet<ShopOrderInfo> queryOrderInfos(String beginDate,String endDate);
	 public PubResult<Integer>  saveOrUpdate(Torder order);
}
