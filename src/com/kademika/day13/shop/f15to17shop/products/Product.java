package com.kademika.day13.shop.f15to17shop.products;

import com.kademika.day13.shop.f15to17shop.Department;

public class Product {
	protected String model;
	protected String producer;
	protected String color;
	protected String character;
	protected double price;
	protected Department dept;
	protected String id;
	protected int number;
	protected boolean isDeleted;

	public Product(String id, String model, String producer, String character,
			String color, int number, double price) {
		this.model = model;
		this.producer = producer;
		this.character = character;
		this.color = color;
		this.price = price;
		this.id = id;
		this.number = number;
		this.isDeleted=false;
	}

	public String getInfo() {
		return id + " Product: " + model + ", made in " + producer
				+ ", basic characteristics: " + character + ", color: " + color
				+ ", department: " + dept + ", number: " + number + ", price: "
				+ price;
	}
	
	public void getNullCopy(){
		this.isDeleted=true;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCharacter() {
		return character;
	}

	public void setCharacter(String character) {
		this.character = character;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Department getDept() {
		return dept;
	}

	public void setDept(Department dept) {
		this.dept = dept;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isDeleted() {
		return isDeleted;
	}	
}
