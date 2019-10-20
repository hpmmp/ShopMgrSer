package com.shop.ShopMgrSer;

import com.shop.entity.Torder;
import com.alibaba.fastjson.JSON;
import java.util.Date;

/**
 * @author ChenTengfei
 * @date 2019/9/4 9:16
 **/
public class JsonTest {
    public static void main(String[] args) {
        Torder torder = new Torder();
        torder.setId(1001);
        torder.setProductId("1001");
        torder.setProductName("衣服");
        torder.setPrice(new Float(100));
        torder.setOrderNum(2);
        torder.setMoney(new Float(200));
        torder.setOrderTime(new Date());
        String string = JSON.toJSONString(torder);
        System.out.println(string);
        Torder torder2 = JSON.parseObject(string,Torder.class);
        System.out.println(torder2);
    }
}