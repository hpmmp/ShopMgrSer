package com.shop.message;

/**
 * 状态码
 * @author zhangchuyan 
 * @date 2016-04-15
 */
public enum StatusCode {

	/**
	 * 0：操作成功
	 */
	SUCCESS(0,"操作成功"),
	/**
	 * -1：用户不存在
	 */
	NO_USER(-1,"用户不存在"),
	/**
	 * -2：设备不存在
	 */
	NO_DEVICE(-2,"设备不存在"),
	/**
	 * -3：数据不存在
	 */
	DATA_NOTEXISTS(-3,"数据不存在"),
	/**
	 * -4：没权限
	 */
	NO_RIGHT(-4,"没权限"),
	/**
	 * -5：数据格式错误
	 */
	DATA_ERROR(-5,"数据格式错误"),
	/**
	 * -6：请求失败
	 */
	REQUEST_ERROR(-6,"请求失败"),
	 
	/**
	 * -8：参数错误
	 */
	PARAM_ERROR(-8,"参数错误"),
	 
	/**
	 * -10：短信发送失败
	 */
	SENDSMS_ERROR(-10,"短信发送失败"),
	/**
	 * -11：重复操作
	 */
	REPEAT(-11,"重复操作"),
 
	/**
	 * -13：状态错误
	 */
	STATUS_ERROR(-13,"状态错误"),
	/**
	 * -14：操作错误
	 */
	OPERATE_ERROR(-14,"操作错误"),
	/**
	 * -15：文件不存在
	 */
	FILE_NOTEXISTS(-15,"文件不存在"),
	/**
	 * -16：存在关联数据
	 */
	REFDATA_EXISTS(-16,"存在关联数据"),
	/**
	 * -17：数据已存在
	 */
	DATA_EXISTS(-17,"数据已存在"),
	/**
	 * -18：数据超出范围
	 */
	DATA_EXCEED(-18,"数据超出范围"),
	/**
	 * -19：超出限制条件
	 */
	CONDIT_EXCEED(-19,"超出限制条件"),
 
	/**
	 * -22：余额不足
	 */
	NO_MONEY(-22,"余额不足"),
	 
	/**
	 * -23：时间段超出范围
	 */
	DATETIMEOUTSIDE(-23,"时间段超出范围"),
	/**
	 * -98：请求超时
	 */
	TIMEOUT(-98,"请求超时"),
	/**
	 * -99：系统异常
	 */
	EXCEPTION(-99,"请求失败，请重试");

	private int code;
	private String desc;
	
	StatusCode(int code,String desc){
		this.code = code;
		this.desc = desc;
	}

	public int code(){
		return code;
	}
	public String desc(){
		return desc;
	}
}
