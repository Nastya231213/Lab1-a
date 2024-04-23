package com.entity;

public class Developer {

	private String name;
	private int id;

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

	
	public Developer(String name) {
		super();
		this.name = name;
	}
	
	public Developer(String name, int id) {
		super();
		this.name = name;
	}
	

}
