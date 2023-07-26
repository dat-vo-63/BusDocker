package com.bus.demo.repo;

import java.util.List;
import java.util.Optional;

import com.bus.demo.entity.Seat;
import com.bus.demo.entity.Ticket;

public interface ITicket {
public String saveTicket(List<Long> seatId);
}
