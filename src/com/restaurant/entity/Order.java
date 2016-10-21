package com.restaurant.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Order {

	private int oid;
	private Calendar odate;
	private int oquant;
	private float ofee;
	private int ocheck;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Calendar getOdate() {
		return odate;
	}

	public void setOdate(Calendar odate) {
		this.odate = odate;
	}

	public int getOquant() {
		return oquant;
	}

	public void setOquant(int oquant) {
		this.oquant = oquant;
	}

	public float getOfee() {
		return ofee;
	}

	public void setOfee(float ofee) {
		this.ofee = ofee;
	}

	public int getOcheck() {
		return ocheck;
	}

	public void setOcheck(int ocheck) {
		this.ocheck = ocheck;
	}

	public Order(int oid, Calendar odate, int oquant, float ofee, int ocheck) {
		super();
		this.oid = oid;
		this.odate = odate;
		this.oquant = oquant;
		this.ofee = ofee;
		this.ocheck = ocheck;
	}

	public Order() {
		super();
	}

	public String fmtDate() {
		if (odate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(odate.getTime());
	}

	public String fmtDate(String fmt) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(fmt);
			return sdf.format(odate.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
