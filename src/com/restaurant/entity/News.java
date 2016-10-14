package com.restaurant.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class News {
	
	private int nid;
	private String nauthor, ncontent, ntag;
	private Calendar ndate;

	public int getNid() {
		return nid;
	}

	public void setNid(int nid) {
		this.nid = nid;
	}

	public String getNauthor() {
		return nauthor;
	}

	public void setNauthor(String nauthor) {
		this.nauthor = nauthor;
	}

	public String getNcontent() {
		return ncontent;
	}

	public void setNcontent(String ncontent) {
		this.ncontent = ncontent;
	}

	public String getNtag() {
		return ntag;
	}

	public void setNtag(String ntag) {
		this.ntag = ntag;
	}

	public Calendar getNdate() {
		return ndate;
	}

	public void setNdate(Calendar ndate) {
		this.ndate = ndate;
	}

	public News(int nid, String nauthor, Calendar ndate, String ncontent,
			String ntag) {
		super();
		this.nid = nid;
		this.nauthor = nauthor;
		this.ndate = ndate;
		this.ncontent = ncontent;
		this.ntag = ntag;
	}

	public News() {
		super();
	}

	public String fmtDate() {
		if (ndate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(ndate.getTime());
	}

	public String fmtDate(String fmt) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(ndate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
