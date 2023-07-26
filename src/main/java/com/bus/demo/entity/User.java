package com.bus.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "userrss")
public class User {
@Id
@GeneratedValue
private long userId;
@Column(name = "user_name")
private String userName;
@Column ( name = "email",unique = true,nullable = false )
private String email;
private String password;
@Column(name = "phone_number",unique = true,nullable = false)
private String phoneNumber;
private  String address;
private String isActive;
private int userBalance;



public int getUserBalance() {
	return userBalance;
}
public void setUserBalance(int userBalance) {
	this.userBalance = userBalance;
}
private String role;
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}
public long getUserId() {
	return userId;
}
public void setUserId(long userId) {
	this.userId = userId;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
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
public String getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getAddress() {
	return address;
}
public void setAddress(String address) {
	this.address = address;
}
public String getIsActive() {
	return isActive;
}
public void setIsActive(String isActive) {
	this.isActive = isActive;
}
}
