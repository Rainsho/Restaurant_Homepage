package com.restaurant.entity;

public class Homepage {

	private int hid;
	private String pic, title, contents, type;

	public int getHid() {
		return hid;
	}

	public void setHid(int hid) {
		this.hid = hid;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Homepage(int hid, String pic, String title, String contents,
			String type) {
		super();
		this.hid = hid;
		this.pic = pic;
		this.title = title;
		this.contents = contents;
		this.type = type;
	}

	public Homepage() {
		super();
	}

}
