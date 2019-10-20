package com.shop.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ChenTengfei
 * @date 2019/9/5 14:12
 **/
public class Tstatistics implements Serializable {
    private static final long serialVersionUID = 7998142595278058934L;

    /**
     * 序号
     */
    private Integer id;
    /**
     *商品ID
     */
    private String productId;
    /**
     *商品名称
     */
    private String productName;
    /**
     * 数量
     */
    private Integer orderNum;
    /**
     * 总价
     */
    private Float money;
    /**
     * 更新时间
     */
    private Date updateTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Float getMoney() {
        return money;
    }

    public void setMoney(Float money) {
        this.money = money;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Tstatistics{" +
                "id=" + id +
                ", productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", orderNum=" + orderNum +
                ", money=" + money +
                ", updateTime=" + updateTime +
                '}';
    }
}