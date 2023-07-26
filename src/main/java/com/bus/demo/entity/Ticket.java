package com.bus.demo.entity;

import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "ticket")
public class Ticket {
	

	

	@Id
	@GeneratedValue
	private long ticketId;
	@OneToOne(targetEntity = Bill.class, cascade = CascadeType.ALL)
	@JoinColumn(name = "billId", referencedColumnName = "billId")
	private Bill bill;
	@JsonBackReference
	@OneToMany(mappedBy = "ticket", fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Seat> seats;

	public long getTicketId() {
		return ticketId;
	}

	public void setTicketId(long ticketId) {
		this.ticketId = ticketId;
	}

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

}
