package com.bus.demo.entity;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnoreType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
//@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Table(name = "seat")
public class Seat implements Comparable<Seat> {
	@Override
	public String toString() {
		return "Seat [seatNo=" + seatNo + ", seatPrice=" + seatPrice + "]";
	}
	@Id
	@GeneratedValue
	private  long seatId;
	@Column(name = "seatNo")
	private String seatNo;
	@Column (name = "seatPrice")
	private int seatPrice;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ticketId",referencedColumnName = "ticketId")
	private Ticket ticket;
	
	public Seat(String seatNo) {
		super();
		this.seatNo = seatNo;
	}
	public Seat(long seatId) {
		super();
		this.seatId = seatId ;
	}
	public Seat() {
		super();
	}
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "scheduleId")
	@JsonBackReference
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "scheduleId")
	private Schedual schedual;
	public Schedual getSchedual() {
		return schedual;
	}
	public void setSchedual(Schedual schedual) {
		this.schedual = schedual;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public int getSeatPrice() {
		return seatPrice;
	}
	public void setSeatPrice(int seatPrice) {
		this.seatPrice = seatPrice;
	}
	public long getSeatId() {
		return seatId;
	}
	public void setSeatId(long seatId) {
		this.seatId = seatId;
	}
	public String getSeatNo() {
		return seatNo;
	}
	public void setSeatNo(String seatNo) {
		this.seatNo = seatNo;
	}
	@Override
	public int compareTo(Seat o) {
		
		return (int) (this.getSeatId()-o.seatId);
	}
	@Override
	public int hashCode() {
		return Objects.hash(seatId);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Seat other = (Seat) obj;
		return Objects.equals(schedual, other.schedual) && seatId == other.seatId
				&& Objects.equals(seatNo, other.seatNo) && seatPrice == other.seatPrice
				&& Objects.equals(ticket, other.ticket);
	}

}
