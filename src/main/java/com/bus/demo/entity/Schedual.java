package com.bus.demo.entity;

import java.util.List;
import java.util.Objects;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

//@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
@Entity
@Table(name = "schedule")
public class Schedual implements Comparable<Schedual> {
	@Id
	@GeneratedValue
	private Long scheduleId;
	@Column(name = "start_time")
	@Temporal(TemporalType.TIME)
	private String startTime;
	@Override
	public int hashCode() {
		return Objects.hash(bus, departure, destinations, endTime, scheduleId, seatLeft, seats, startDate, startTime,
				totalSeat);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Schedual other = (Schedual) obj;
		return Objects.equals(bus, other.bus) && Objects.equals(departure, other.departure)
				&& Objects.equals(destinations, other.destinations) && Objects.equals(endTime, other.endTime)
				&& Objects.equals(scheduleId, other.scheduleId) && seatLeft == other.seatLeft
				&& Objects.equals(seats, other.seats) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(startTime, other.startTime) && totalSeat == other.totalSeat;
	}

	@Column(name = "end_time")
	private String endTime;
	@Column(name = "totalSeat")
	private int totalSeat;
	@Column(name = "seatLeft")
	private int seatLeft;
	@Column(name = "startDate")
	private String startDate;
	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestinations() {
		return destinations;
	}

	public void setDestinations(String destinations) {
		this.destinations = destinations;
	}

	@Column(name = "departure ")
	private String departure;
	@Column (name = "destinations")
	private String destinations;
	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public int getSeatLeft() {
		return seatLeft;
	}

	@Override
	public String toString() {
		return "Schedual [scheduleId=" + scheduleId + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", totalSeat=" + totalSeat + ", seatLeft=" + seatLeft + ", bus=" + bus.toString() + ", seats=" + "]";
	}

	public void setSeatLeft(int seatLeft) {
		this.seatLeft = seatLeft;
	}
	
	@ManyToOne(targetEntity = Bus.class, cascade = CascadeType.MERGE)
	@JoinColumn(name = "busId")
	private Bus bus;

	public int getTotalSeat() {
		return totalSeat;
	}

	public void setTotalSeat(int totalSeat) {
		this.totalSeat = totalSeat;
	}

	
	 
	@JsonManagedReference
	@JsonIdentityInfo(
			  generator = ObjectIdGenerators.PropertyGenerator.class, 
			  property = "seatId")
	@OneToMany(mappedBy = "schedual", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Seat> seats;

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}

	public Long getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(Long scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	@Override
	public int compareTo(Schedual o) {
		// TODO Auto-generated method stub
		return 0;
	}
}
