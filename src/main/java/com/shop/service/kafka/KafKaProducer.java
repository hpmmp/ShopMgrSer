package com.shop.service.kafka;

import java.text.SimpleDateFormat;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.shop.entity.Torder;

/**
 * @author ChenTengfei
 * @date 2019/8/19 16:45
 **/

@Service
public class KafKaProducer {

    @Autowired
    private KafkaTemplate<Integer, String> kafkaTemplate;

    public String save(Torder order) {
        System.out.println("************生产者向kafka发送数据****************");
        String string = JSON.toJSONString(order);
        kafkaTemplate.sendDefault(string);
        return null;
    }

}