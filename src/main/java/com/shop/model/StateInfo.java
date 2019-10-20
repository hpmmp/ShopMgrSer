package com.shop.model;

import java.io.Serializable;

public class StateInfo implements Serializable {

	private static final long serialVersionUID = 2L;
	public Integer state;
	 
	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
	 
}
