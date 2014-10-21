package com.kademika.day13.shop.f15to17shop.personal;

public enum JobRole {
	CONSULTANT(0), PAYMASTER(1);
	private int id;

	private JobRole(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
