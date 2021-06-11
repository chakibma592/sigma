package com.optimgov.spring.elearning.payload.response;

import java.util.ArrayList;

public class AllCartResponse {
	private ArrayList<CartResponse> cartlist;
	private int count;
	private double cost;
	public ArrayList<CartResponse> getCartlist() {
		return cartlist;
	}
	public void setCartlist(ArrayList<CartResponse> cartlist) {
		this.cartlist = cartlist;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public AllCartResponse(ArrayList<CartResponse> cartlist, int count, double cost) {
		super();
		this.cartlist = cartlist;
		this.count = count;
		this.cost = cost;
	}
	public AllCartResponse() {
		super();
	}
	
	
}
