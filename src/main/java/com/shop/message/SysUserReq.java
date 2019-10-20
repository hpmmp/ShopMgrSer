package com.shop.message;

import java.util.Date;

public class SysUserReq extends RequestMsg implements java.io.Serializable{

	private Integer id;

	    /**
	     * 账号名
	     */	 
	    private String account;

	    /**
	     * 创建时间
	     */
	    private Date createdTS;
	 
	 

	    /**
	     * 状态：1.有效 2.失效
	     */ 
	    private Short status;

	    /**
	     * 真实姓名
	     */
	    private String name;

	    /**
	     * 密码
	     */
	    private String password;
	   public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public Date getCreatedTS() {
		return createdTS;
	}

	public void setCreatedTS(Date createdTS) {
		this.createdTS = createdTS;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
 

}
