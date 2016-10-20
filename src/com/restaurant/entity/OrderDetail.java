package com.restaurant.entity;

public class OrderDetail {

	private Order order;
	private Food food;
	private int odquant;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Food getFood() {
		return food;
	}

	public void setFood(Food food) {
		this.food = food;
	}

	public int getOdquant() {
		return odquant;
	}

	public void setOdquant(int odquant) {
		this.odquant = odquant;
	}

	public OrderDetail(Order order, Food food, int odquant) {
		super();
		this.order = order;
		this.food = food;
		this.odquant = odquant;
	}

	public OrderDetail() {
		super();
	}

}
