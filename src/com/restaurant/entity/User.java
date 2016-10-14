package com.restaurant.entity;

public class User {

	private int uid, utype, ustaffdisplay;
	private String uname, upassword, utelphone, ustaffinfo, upic;

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public int getUtype() {
		return utype;
	}

	public void setUtype(int utype) {
		this.utype = utype;
	}

	public int getUstaffdisplay() {
		return ustaffdisplay;
	}

	public void setUstaffdisplay(int ustaffdisplay) {
		this.ustaffdisplay = ustaffdisplay;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUpassword() {
		return upassword;
	}

	public void setUpassword(String upassword) {
		this.upassword = upassword;
	}

	public String getUtelphone() {
		return utelphone;
	}

	public void setUtelphone(String utelphone) {
		this.utelphone = utelphone;
	}

	public String getUstaffinfo() {
		return ustaffinfo;
	}

	public void setUstaffinfo(String ustaffinfo) {
		this.ustaffinfo = ustaffinfo;
	}

	public String getUpic() {
		return upic;
	}

	public void setUpic(String upic) {
		this.upic = upic;
	}

	public User(int uid, String uname, String upassword, String utelphone,
			int utype, String ustaffinfo, int ustaffdisplay, String upic) {
		super();
		this.uid = uid;
		this.uname = uname;
		this.upassword = upassword;
		this.utelphone = utelphone;
		this.utype = utype;
		this.ustaffinfo = ustaffinfo;
		this.ustaffdisplay = ustaffdisplay;
		this.upic = upic;
	}

	public User() {
		super();
	}

}
