package com.bus.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.bus.demo.entity.Seat;

public interface ISeat {
	
public List<Seat> findByTicketId(long id);
}
