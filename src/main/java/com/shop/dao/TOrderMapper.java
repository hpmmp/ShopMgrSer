package com.shop.dao;
 

import java.util.List;
 
import com.shop.entity.Torder;
import com.shop.message.OrderInfoReq;

import tk.mybatis.mapper.common.Mapper;

public interface TOrderMapper  extends Mapper<Torder>{
	List<Torder> queryOrderInfos(OrderInfoReq req); 

}
