package com.bus.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bus.demo.entity.Seat;

public interface SeatRepo extends JpaRepository<Seat, Long> {
	public Seat findBySeatId(long seatId);
	@Query ("Select s FROM Seat s where s.ticket.ticketId =:id")
	public List<Seat> findByTicketId(long id);
}
