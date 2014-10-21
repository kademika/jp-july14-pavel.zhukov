package com.kademika.day13.shop.f15to17shop.products;

public enum ComputerType {
	NOTEBOOK(0), PC(1), TABLETPC(2);
	private int id;

	private ComputerType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
