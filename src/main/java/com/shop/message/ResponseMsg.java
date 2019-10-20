package com.shop.message;

import java.io.Serializable;

public class ResponseMsg implements Serializable{
	/** 返回值 */
	private String retCode;

	/** 返回值描述 */
	private String retDesc ;

    private Object retData;
	/** 备注 */
	private String retRemark ;

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetDesc() {
		return retDesc;
	}

	public void setRetDesc(String retDesc) {
		this.retDesc = retDesc;
	}

    public Object getRetData() {
        return retData;
    }

    public void setRetData(Object retData) {
        this.retData = retData;
    }

    public String getRetRemark() {
		return retRemark;
	}

	public void setRetRemark(String retRemark) {
		this.retRemark = retRemark;
	}

}
