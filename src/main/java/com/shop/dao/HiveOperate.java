package com.shop.dao;

import com.shop.entity.LoggingInfoPO;
import com.shop.message.OrderInfoReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenTengfei
 * @date 2019/8/14 20:22
 **/
@Component
public class HiveOperate {
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 批量插入数据到hive
     */
    public void insertLogInfos(List<LoggingInfoPO> loggingInfoPOList){
        String sql = "insert into logging(type,text,time) values(?,?,?)";

        List<Object[]> batchArgs = new ArrayList<>();
        for(LoggingInfoPO loggingInfoPO:loggingInfoPOList){
            batchArgs.add(new Object[]{loggingInfoPO.getType(),loggingInfoPO.getText(),loggingInfoPO.getTime()});
        }
        jdbcTemplate.batchUpdate(sql,batchArgs);
    }
    
    /**
     * 单条插入
     */
    public int insertLogInfo(LoggingInfoPO loggingInfoPO){
        logger.info("into HiveOperate-insertLogInfo");
        logger.info("req: "+loggingInfoPO.toString());
    	String sql = "insert into logging(type,text,time) values(?,?,?)";
    	int updateCount = jdbcTemplate.update(sql,loggingInfoPO.getType(),loggingInfoPO.getText(),loggingInfoPO.getTime());
        System.out.println("更新行数："+updateCount);
        logger.info("end HiveOperate-jdbcTemplate.update");
    	return updateCount;
    }
    
    
    /**
     * 查询特定时间段里的日志信息集合
     * @param req
     * @return List<LoggingInfoPO>
     */
    public List<LoggingInfoPO> queryLogInfos(OrderInfoReq req){
        logger.info("into HiveOperate-queryLogInfos");
        logger.info("req: "+req.toString());
        //开始时间和结束时间格式为yy-mm-dd
        String beginDay = req.getBeginDate();
        String endDay = req.getEndDate();

        beginDay = strFormat(beginDay);
        endDay = strFormat(endDay);
        //String sql = "select type,text,time from logging where time>="+"\""+beginDay+"\""+" and time <="+"\""+endDay+"\" order by time";
        String sql = "select type,text,time from logging where time>="+"\""+beginDay+"\""+" and time <="+"\""+endDay+"\" ";
        RowMapper<LoggingInfoPO> loggingInfoPORowMapper = new BeanPropertyRowMapper<>(LoggingInfoPO.class);
        List<LoggingInfoPO> list = jdbcTemplate.query(sql,loggingInfoPORowMapper);
        for (LoggingInfoPO loggingInfoPO : list) {
            logger.info("loggingInfoPO"+loggingInfoPO.toString());
        }
        logger.info("end HiveOperate-jdbcTemplate.query");
        return list;
    }

    public String strFormat(String time){
        StringBuffer day =new StringBuffer(time);
        //day.insert(4,'-');
        //day.insert(7,'-');
        day.append(" 00:00:00");
        return new String(day);
    }

}