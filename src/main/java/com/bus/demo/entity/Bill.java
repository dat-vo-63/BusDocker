	package com.bus.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "bill")
public class Bill {
@Id
@GeneratedValue
private long billId;
private int totalPrice;
private String billStatus;
public String getBillStatus() {
	return billStatus;
}
public void setBillStatus(String billStatus) {
	this.billStatus = billStatus;
}
//@JsonBackReference
//@OneToOne(targetEntity = Ticket.class,cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//@JoinColumn(name = "billId",referencedColumnName = "billId")
//private Ticket tickets;

//public Ticket getTickets() {
//	return tickets;
//}
//public void setTickets(Ticket tickets) {
//	this.tickets = tickets;
//}
public int getTotalPrice() {
	return totalPrice;
}
public void setTotalPrice(int totalPrice) {
	this.totalPrice = totalPrice;
}
public long getBillId() {
	return billId;
}
public void setBillId(long billId) {
	this.billId = billId;
}
public User getUser() {
	return user;
}
public void setUser(User user) {
	this.user = user;
}
@ManyToOne(targetEntity = User.class,cascade = CascadeType.PERSIST,fetch =  FetchType.EAGER)
@JoinColumn(name = "userId",referencedColumnName = "userId")
private User user;

}
