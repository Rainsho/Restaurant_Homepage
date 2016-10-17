package com.restaurant.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Reservation {

	private int resid, resseat;
	private User user;
	private String restitle;
	private Calendar resdate;
	private String resinfo;

	public int getResid() {
		return resid;
	}

	public void setResid(int resid) {
		this.resid = resid;
	}

	public int getResseat() {
		return resseat;
	}

	public void setResseat(int resseat) {
		this.resseat = resseat;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getRestitle() {
		return restitle;
	}

	public void setRestitle(String restitle) {
		this.restitle = restitle;
	}

	public Calendar getResdate() {
		return resdate;
	}

	public void setResdate(Calendar resdate) {
		this.resdate = resdate;
	}

	public String getResinfo() {
		return resinfo;
	}

	public void setResinfo(String resinfo) {
		this.resinfo = resinfo;
	}

	public Reservation(int resid, User user, String restitle, Calendar resdate,
			String resinfo, int resseat) {
		super();
		this.resid = resid;
		this.user = user;
		this.restitle = restitle;
		this.resdate = resdate;
		this.resinfo = resinfo;
		this.resseat = resseat;
	}

	public Reservation() {
		super();
	}
	
	public String fmtDate() {
		if (resdate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(resdate.getTime());
	}

	public String fmtDate(String fmt) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(resdate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
