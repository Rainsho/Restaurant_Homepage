package com.restaurant.entity;

public class MenuItem {

	private Food food;
	private int quant;

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getQuant() {
		return quant;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public MenuItem(Food food, int quant) {
		super();
		this.food = food;
		this.quant = quant;
	}

	public MenuItem() {
		super();
	}

}
