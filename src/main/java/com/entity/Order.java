package com.entity;

public class Order {

	private String email;
	private String address;
	private String fullName;
	private String city;
	private Double price;
	private String phone;
	private String date;
	private int orderId;
	private String state;
	private String status;
	private int userId;
	public Order() {
		
	}
	public Order(String email, String address, String fullName, String city, Double price, String phone, String date,
			String state, int userId) {
		super();
		this.email = email;
		this.address = address;
		this.fullName = fullName;
		this.city = city;
		this.price = price;
		this.phone = phone;
		this.date = date;
		this.state = state;
		this.userId = userId;
	}
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public Order(String email, String address, String fullName, String city, Double price, String phone, String date,
			String state) {
		super();
		this.email = email;
		this.address = address;
		this.fullName = fullName;
		this.city = city;
		this.price = price;
		this.phone = phone;
		this.date = date;
		this.state = state;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
