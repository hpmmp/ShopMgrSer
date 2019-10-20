package com.shop.message;

import java.io.Serializable;

/**
 * 统一返回结果（对象）
 * @author zhangchuyan
 * @date 2016-04-15
 */

public class PubResult<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer code; //返回结果标识，0x00成功，其它失败
	private String desc;

	private T data; //返回内容

	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isSuccessful() {
		if(null != code 
				&& 0 == code)
			return true;
		else
			return false;
	}
}
