package com.entity;

public class Developer {

	private String name;
	private int id;
	private int userId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Developer(String name, int userId) {
		super();
		this.name = name;
		this.userId = userId;
	}
	

}
