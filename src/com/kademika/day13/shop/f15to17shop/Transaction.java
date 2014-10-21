package com.kademika.day13.shop.f15to17shop;

import com.kademika.day13.shop.f15to17shop.personal.Personal;
import com.kademika.day13.shop.f15to17shop.products.Product;

public class Transaction {
	private int numTransaction;
	private Client client;
	private Product prod;
	private int number;
	private Personal consultant;
	private Personal paymaster;
	private String model;
	private double price;
	private double totalPrice;
	private String idProd;

	public Transaction(Client client, Product prod,
			int number, Personal consultant, Personal paymaster, int day) {
		this.numTransaction=getNumTransaction();
		this.client = client;
		this.idProd= prod.getId();
		this.model=prod.getModel();
		this.price=prod.getPrice();
		this.number = number;
		this.totalPrice=prod.getPrice()*number;
		this.consultant = consultant;
		this.paymaster = paymaster;
	}

	public int getNumTransaction() {
		return numTransaction;
	}

	public void setNumTransaction(int numTransaction) {
		this.numTransaction = numTransaction;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Product getProd() {
		return prod;
	}

	public void setProd(Product prod) {
		this.prod = prod;
	}

	public int getNumber() {
		return number;
	}

	public void setSNumber(int number) {
		this.number = number;
	}

	public Personal getConsultant() {
		return consultant;
	}

	public void setConsultant(Personal consultant) {
		this.consultant = consultant;
	}

	public Personal getPaymaster() {
		return paymaster;
	}

	public void setPaymaster(Personal paymaster) {
		this.paymaster = paymaster;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getIdProd() {
		return idProd;
	}

	public void setIdProd(String idProd) {
		this.idProd = idProd;
	}	

}
