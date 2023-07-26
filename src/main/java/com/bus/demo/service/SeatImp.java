package com.bus.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Seat;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.SeatRepo;

@Service
public class SeatImp implements ISeat {
@Autowired
SeatRepo repo;
	@Override
	public List<Seat> findByTicketId(long id) {
		return repo.findByTicketId(id);
	}

}
