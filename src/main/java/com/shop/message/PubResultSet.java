package com.shop.message;

import java.io.Serializable;
import java.util.List;

/**
 * 统一返回结果（列表）
 * @author zhangchuyan
 * @date 2016-04-15
 */

public class PubResultSet<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer code; //返回结果标识，0x00成功，其它失败
	private String desc;

	private List<T> data; //返回内容
	
	private int totalRecords;//总记录数

	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}

	public boolean isSuccessful() {
		if(null != code 
				&& 0 == code)
			return true;
		else
			return false;
	}
	public int getTotalRecords() {
		
		if( 0 < totalRecords){
			return totalRecords;
		}else if(null != data){
			return data.size();
		}else{
			return 0;
		}
	}
	public void setTotalRecords(int totalRecords) {
		this.totalRecords = totalRecords;
	}
}
