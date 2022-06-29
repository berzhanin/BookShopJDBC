package com.bookstore.entity;

public class Users implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer userId;
	private String email;
	private String password;
	private String fullName;

	public Users() {
	}
	
	public Users(int userId) {
		this.userId = userId;
	}

	public Users(String email, String fullName, String password) {
		this.email = email;
		this.fullName = fullName;
		this.password = password;
	}

	public Users(Integer userId, String email, String fullName, String password) {
		this(email,fullName,password);
		this.userId = userId;
	}
	
	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return this.fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
}
