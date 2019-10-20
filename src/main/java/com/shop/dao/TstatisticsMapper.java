package com.shop.dao;


import com.shop.entity.Tstatistics;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TstatisticsMapper extends Mapper<Tstatistics>{
	List<Tstatistics> queryStatisticsInfos();
}
