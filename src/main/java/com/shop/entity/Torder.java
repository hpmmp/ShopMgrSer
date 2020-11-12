package com.shop.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_order")
public class Torder implements Serializable {

	/**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
 
    /**
     *商品ID
     */
    @Column(name = "productId")
    private String productId;
    
    /**
     *商品名称
     */
    @Column(name = "productName")
    private String productName;
    

    /**
     * 单价
     */
    @Column(name = "price")
    private Float price;
    
    /**
     * 数量
     */
    @Column(name = "orderNum")
    private Integer orderNum;

    /**
     * 总价
     */
    @Column(name = "money")
    private Float money;
    
    /**
     * 订购时间
     */
    @Column(name = "orderTime")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date orderTime;
 
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

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
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

	public Date getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}

	  public String toJsonString(){
			return "{\"id\":\""+id+"\",\"productId\":\""+productId+"\",\"productName\":\""+productName+"\",\"price\":"+price+",\"orderNum\":"+orderNum+",\"money\":\""+money+"\"}"
	    	;
		
	    }
}
