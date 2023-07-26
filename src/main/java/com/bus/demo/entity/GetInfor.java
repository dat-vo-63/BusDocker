package com.bus.demo.entity;

import java.util.List;
import java.util.Objects;

import lombok.Data;

@Data
public class GetInfor implements Comparable<GetInfor>{
@Override
	public int hashCode() {
		return Objects.hash(billId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GetInfor other = (GetInfor) obj;
		return billId == other.billId;
	}
private long billId;
private String startDate;
private String startTime;
private List<Seat> seatNumberList;
private int price;
private String destination;
private String departure;
private String status;
private String userName;
private String userPhone;
private String busName;
public String getDestination() {
	return destination;
}
public void setDestination(String destination) {
	this.destination = destination;
}
public String getDeparture() {
	return departure;
}
public void setDeparture(String departure) {
	this.departure = departure;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getUserName() {
	return userName;
}
public void setUserName(String userName) {
	this.userName = userName;
}
public String getUserPhone() {
	return userPhone;
}
public void setUserPhone(String userPhone) {
	this.userPhone = userPhone;
}
public String getBusName() {
	return busName;
}
public void setBusName(String busName) {
	this.busName = busName;
}
public long getBillId() {
	return billId;
}
public void setBillId(long billId) {
	this.billId = billId;
}
public String getStartDate() {
	return startDate;
}
public void setStartDate(String startDate) {
	this.startDate = startDate;
}
public String getStartTime() {
	return startTime;
}
public void setStartTime(String startTime) {
	this.startTime = startTime;
}
public List<Seat> getSeatNumber() {
	return seatNumberList;
}
public void setSeatNumber(List<Seat> seats) {
	this.seatNumberList = seats;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
@Override
public int compareTo(GetInfor o) {
	return (int) (this.billId - o.billId);
}
}
