package com.kademika.day13.shop.f15to17shop.personal;

public class Personal {
	private String fio;
	private String email;
	private int age;
	protected JobRole role;
	
	public Personal(String fio, int age) {
		this(fio, age, "info@shop.com", JobRole.CONSULTANT);
	}

	public Personal(String fio, int age, String email, JobRole role) {
		this.fio = fio;
		this.age = age;
		this.email = email;
		this.role= role;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public JobRole getRole() {
		return role;
	}

	public void setRole(JobRole role) {
		this.role = role;
	}	

}
