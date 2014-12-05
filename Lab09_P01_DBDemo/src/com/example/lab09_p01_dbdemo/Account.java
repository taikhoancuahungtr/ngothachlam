package com.example.lab09_p01_dbdemo;

public class Account {

	private int id;
	private String username;
	private String password;
	private String address;
	
	public Account(int id, String username, String password, String address) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return id + " - " + username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
