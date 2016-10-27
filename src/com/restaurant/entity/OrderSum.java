package com.restaurant.entity;

public class OrderSum {

	private int count, sum_quant;
	private float sum_fee;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getSum_quant() {
		return sum_quant;
	}

	public void setSum_quant(int sum_quant) {
		this.sum_quant = sum_quant;
	}

	public float getSum_fee() {
		return sum_fee;
	}

	public void setSum_fee(float sum_fee) {
		this.sum_fee = sum_fee;
	}

	public OrderSum(int count, int sum_quant, float sum_fee) {
		super();
		this.count = count;
		this.sum_quant = sum_quant;
		this.sum_fee = sum_fee;
	}

	public OrderSum() {
		super();
	}

}
