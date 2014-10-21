package com.kademika.day13.shop.f15to17shop.products;

import com.kademika.day13.shop.f15to17shop.Department;

public class Phone extends Product {
	private String os;
	private String resolution;
	private double diagonal;
	private String cardType;
	private PhoneType pt;

	public Phone(String id, String model, PhoneType pt, String producer,
			String character, double diagonal, String resolution, String os,
			String cardType, String color, int number, double price) {
		super(id, model, producer, character, color, number, price);
		dept = Department.PHONES;
		this.pt = pt;
		this.diagonal = diagonal;
		this.resolution = resolution;
		this.os = os;
		this.cardType = cardType;
	}

	@Override
	public String getInfo() {
		if (pt == PhoneType.SMARTPHONE) {
			return id + " Smartphone: " + model + ", made in "
					+ producer + ", basic characteristics: " + character
					+ ", diagonal: " + diagonal + "'', resolution: "
					+ resolution + ", OS: " + os + ", card type: " + cardType
					+ ", \ncolor: " + color + ", department: " + dept
					+ "\nnumber: " + number + " \nprice: " + price + " rub";
		} else if (pt == PhoneType.MOBILEPHONE) {
			return id + " Mobile phone: " + model + ", made in "
					+ producer + ", basic characteristics: " + character
					+ ", diagonal: " + diagonal + "'', resolution: "
					+ resolution + ", OS: " + os + ", card type: " + cardType
					+ ", \ncolor: " + color + ", department: " + dept
					+ "\nnumber: " + number + " \nprice: " + price + " rub";
		} else {
			return id + " Radio phone: " + model + ", made in "
					+ producer + ", basic characteristics: " + character
					+ ", diagonal: " + diagonal + "'', resolution: "
					+ resolution + ", OS: " + os + ", card type: " + cardType
					+ ", \ncolor: " + color + ", department: " + dept
					+ "\nnumber: " + number + " \nprice: " + price + " rub";
		}
	}

	public String getOs() {
		return os;
	}

	public String getResolution() {
		return resolution;
	}

	public double getDiagonal() {
		return diagonal;
	}

	public String getCardType() {
		return cardType;
	}

	public PhoneType getPt() {
		return pt;
	}
}
