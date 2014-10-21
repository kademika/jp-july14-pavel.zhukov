package com.kademika.day13.shop.f15to17shop.products;

public enum PhoneType {
	SMARTPHONE(0), MOBILEPHONE(1), RADIOPHONE(2);

	private int id;

	private PhoneType(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
