package com.shop.service.impl;

import com.shop.dao.TOrderMapper;
import com.shop.entity.Torder;
import com.shop.message.OrderInfoReq;
import com.shop.message.PubResult;
import com.shop.message.PubResultSet;
import com.shop.message.StatusCode;
import com.shop.model.ShopOrderInfo;
import com.shop.service.IShopOrder;
import com.shop.service.kafka.KafKaProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ShopOrderImpl implements IShopOrder {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private TOrderMapper orderMapper;
	
	@Autowired
	KafKaProducer kafKaProducer;

	@Override
	public PubResultSet<ShopOrderInfo> queryOrderInfos(String beginDate, String endDate) {
		OrderInfoReq req = new OrderInfoReq();
		req.setBeginDate(beginDate);
		req.setEndDate(endDate);
		List<Torder> dbList = orderMapper.queryOrderInfos(req);
		PubResultSet<ShopOrderInfo> obdResultSet = new PubResultSet<ShopOrderInfo>();
		List<ShopOrderInfo> data = new ArrayList<ShopOrderInfo>();
		if (null != dbList && 0 < dbList.size()) {
			for (Torder order : dbList) {
				data.add(convertOrderInfoModel(order));
			}
		}

		obdResultSet.setCode(StatusCode.SUCCESS.code());
		obdResultSet.setDesc(StatusCode.SUCCESS.desc());
		obdResultSet.setData(data);

		return obdResultSet;
	}

	private ShopOrderInfo convertOrderInfoModel(Torder order) {

		ShopOrderInfo info = new ShopOrderInfo();
		info.setId(order.getId());
		info.setMoney(order.getMoney());
		info.setOrderNum(order.getOrderNum());
		info.setOrderTime(order.getOrderTime());
		info.setPrice(order.getPrice());
		info.setProductId(order.getProductId());
		info.setProductName(order.getProductName());
		return info;
	}

	@Override
	 public PubResult<Integer>  saveOrUpdate(Torder order) {
		PubResult<Integer> obdResult = new PubResult<Integer>();
		obdResult.setCode(StatusCode.EXCEPTION.code());
		obdResult.setDesc(StatusCode.EXCEPTION.desc());
		 
		if (order.getId() == null) {
			order.setOrderTime(new Date());

			orderMapper.insertSelective(order);
			 
		}else{
			orderMapper.updateByPrimaryKeySelective(order);
		}
		//调用kafka生产者服务
		kafKaProducer.save(order);
		obdResult.setCode(StatusCode.SUCCESS.code());
		obdResult.setDesc(StatusCode.SUCCESS.desc());
		 
		return obdResult;
	}

}
