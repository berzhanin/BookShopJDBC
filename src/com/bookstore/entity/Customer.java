package com.bookstore.entity;

public class Customer implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private int customerId;
	private String email;
	private String fullname;
	private String adress;
	private String password;

	public Customer() {
	}
	
	public Customer(int customerId) {
		this.customerId = customerId;
	}

	public Customer(int customerId, String email, String fullname, String adress, String password) {
		this.customerId = customerId;
		this.email = email;
		this.fullname = fullname;
		this.adress = adress;
		this.password = password;
	}

	public Customer(int customerId,String email, String password, String fullname ) {
		this.customerId = customerId;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}
	
	public Customer(String email, String password,String fullname) {
		this.email = email;
		this.password = password;
		this.fullname = fullname;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getAdress() {
		return this.adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
