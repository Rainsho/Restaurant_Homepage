package com.restaurant.entity;

public class Picture {
	
	private int pid, pdisplay;
	private String pname, ppath;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public int getPdisplay() {
		return pdisplay;
	}

	public void setPdisplay(int pdisplay) {
		this.pdisplay = pdisplay;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPpath() {
		return ppath;
	}

	public void setPpath(String ppath) {
		this.ppath = ppath;
	}

	public Picture(int pid, String pname, String ppath, int pdisplay) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.ppath = ppath;
		this.pdisplay = pdisplay;
	}

	public Picture() {
		super();
	}

}
