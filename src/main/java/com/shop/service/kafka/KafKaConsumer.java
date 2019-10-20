package com.shop.service.kafka;

import com.alibaba.fastjson.JSON;
import com.shop.dao.HbaseOperate;
import com.shop.dao.HiveOperate;
import com.shop.entity.LoggingInfoPO;
import com.shop.entity.Torder;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ChenTengfei
 * @date 2019/8/19 16:46
 **/
public class KafKaConsumer implements MessageListener<Integer, String> {

    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
    HiveOperate hiveOperate;
	@Autowired
    HbaseOperate hbaseOperate;
	
	@Override
    public void onMessage(ConsumerRecord<Integer, String> records) {
        logger.info("消费者消费kafka数据");
        System.out.println("==========消费者消费kafka数据==========");
        System.out.println(records.value());
        Torder torder = JSON.parseObject(records.value(),Torder.class);

        Date time = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println("text:"+"订购了编号{"+torder.getProductId()+"}的"+torder.getProductName());
        System.out.println("time:"+sdf.format(time));
        LoggingInfoPO loggingInfoPO = new LoggingInfoPO();
        loggingInfoPO.setType("type1");
        loggingInfoPO.setText("订购了编号{"+torder.getProductId()+"}的"+torder.getProductName());
        loggingInfoPO.setTime(sdf.format(time));
        System.out.println(loggingInfoPO);
        hiveOperate.insertLogInfo(loggingInfoPO);
        logger.info("hive日志记录完成");
        System.out.println("hive日志记录完成");
        //hbase写入
        try {
            hbaseOperate.put(torder);
            logger.info("hbase统计记录完成");
            System.out.println("hbase统计记录完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}