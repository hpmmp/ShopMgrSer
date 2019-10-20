package com.shop.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

public class ShopOrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;
 

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
	     * 单价
	     */
	 
	    private Float price;
	    
	    /**
	     * 数量
	     */
	  
	    private Integer orderNum;

	    /**
	     * 总价
	     */
	   
	    private Float money;
	    
	    /**
	     * 订购时间
	     */
 
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

}
