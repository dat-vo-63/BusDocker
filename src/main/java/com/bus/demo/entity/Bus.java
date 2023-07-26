package com.bus.demo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus implements Comparable<Bus>{
@Override
	public String toString() {
		return "Bus [busId=" + busId + ", name=" + name + ", seat=" + seat + ", fromLocate=" + "]";
	}



@Id
@GeneratedValue
private long busId;
@Column(name = "bus_name")
private String name;
@Column(name = "bus_seat")
private int seat;
@Override
public int hashCode() {
	return Objects.hash(  name, seat);
}





@Column(name = "imageBus")
private String imageBus;
public String getImageBus() {
	return imageBus;
}

public void setImageBus(String imageBus) {
	this.imageBus = imageBus;
}

public long getBusId() {
	return busId;
}





public void setBusId(long busId) {
	this.busId = busId;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getSeat() {
	return seat;
}
public void setSeat(int seat) {
	this.seat = seat;
}



@Override
public int compareTo(Bus o) {
	// TODO Auto-generated method stub
	return (int) (this.getBusId()-o.busId);
}
}
