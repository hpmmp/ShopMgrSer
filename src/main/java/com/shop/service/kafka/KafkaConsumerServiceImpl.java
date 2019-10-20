package com.shop.service.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.MessageListener;

/**
 * @author ChenTengfei
 * @date 2019/8/13 17:50
 **/
public class KafkaConsumerServiceImpl implements MessageListener<String,String> {

    @Override
    public void onMessage(ConsumerRecord<String, String> data) {
        //根据不同主题，消费
        System.out.println("========");
        if("first".equals(data.topic())){
            //逻辑1
            System.out.println(data.value()+"first 被消费");
        }else if("second".equals(data.topic())){
            //逻辑2
            System.out.println(data.value()+"second 被消费");
        }
        else if("third".equals(data.topic())){
            //逻辑2
            System.out.println(data.value()+"third 被消费");
        }
    }
}