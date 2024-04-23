package com.entity;

import java.util.Date;

public class User {
private int user_id;
private String username;
private String email;
private String password;
private String fullName;
private String phone_number;
private String address;
private String registration_date;
private String Hash;
public User() {
	
}
public User(String username,String email, String password,
		String fullName,String phone_number,String address,String registration_date) {
	this.username=username;
	this.email=email;
	this.password=password;
	this.fullName=fullName;
	this.phone_number=phone_number;
	this.address=address;
	this.registration_date=registration_date;
}

public String getHash() {
	return Hash;
}
public void setHash(String hash) {
	Hash = hash;
}
public int getUser_id() {
	return user_id;
}
public void setUser_id(int user_id) {
	this.user_id = user_id;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}
public String getFullName() {
	return fullName;
}
public void setFullName(String fullName) {
	this.fullName = fullName;
}
public String getPhone_number() {
	return phone_number;
}
public void setPhone_number(String phone_number) {
	this.phone_number = phone_number;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getRegistration_date() {
	return registration_date;
}
public void setRegistration_date(String registration_date) {
	this.registration_date = registration_date;
}


}
