package com.kademika.day13.shop.f15to17shop;

public class Client {
	private String fio;
	private String email;
	private String tel;

	public Client(String fio, String email, String tel) {
		this.fio = fio;
		this.email = email;
		this.tel = tel;
	}

	public String getFio() {
		return fio;
	}

	public void setFio(String fio) {
		this.fio = fio;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}
