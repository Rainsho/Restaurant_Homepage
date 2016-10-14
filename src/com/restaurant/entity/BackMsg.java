package com.restaurant.entity;

public class BackMsg {
	private int state;
	private String msg;
	private Object obj;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public BackMsg(int state, String msg, Object obj) {
		super();
		this.state = state;
		this.msg = msg;
		this.obj = obj;
	}

	public BackMsg() {
		super();
	}

}
