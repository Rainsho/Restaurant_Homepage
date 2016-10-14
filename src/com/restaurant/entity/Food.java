package com.restaurant.entity;

public class Food {
	
	private int fid;
	private FoodType foodtype;
	private Picture picture;
	private String fname, fdetial;
	private float fprice;

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public FoodType getFoodtype() {
		return foodtype;
	}

	public void setFoodtype(FoodType foodtype) {
		this.foodtype = foodtype;
	}

	public Picture getPicture() {
		return picture;
	}

	public void setPicture(Picture picture) {
		this.picture = picture;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getFdetial() {
		return fdetial;
	}

	public void setFdetial(String fdetial) {
		this.fdetial = fdetial;
	}

	public float getFprice() {
		return fprice;
	}

	public void setFprice(float fprice) {
		this.fprice = fprice;
	}

	public Food(int fid, FoodType foodtype, Picture picture, String fname,
			String fdetial, float fprice) {
		super();
		this.fid = fid;
		this.foodtype = foodtype;
		this.picture = picture;
		this.fname = fname;
		this.fdetial = fdetial;
		this.fprice = fprice;
	}

	public Food() {
		super();
	}

}
