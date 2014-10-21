package com.kademika.day13.shop.f15to17shop.products;

public enum VideoType {
	TV(0), PLAYER(1), PROJECTOR(2);

	private int id;

	private VideoType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
