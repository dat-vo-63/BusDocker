package com.bus.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Seat;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;
@RestController
@CrossOrigin(value = "*")
public class SeatController {
	@Autowired
	IBus busRepo;
	@Autowired
	IBus Ibus;
	@Autowired
	ScheduleService schedule;
	@Autowired
	TicketService ticketService;
	@Autowired
	ISeat iSeat;
	@Autowired
	IBill iBill;
	@Autowired
	IUser iUser;
	@GetMapping("/find-seat/{id}")
	public List<Seat> findSeatByTicket(@PathVariable long id)
	{
		return iSeat.findByTicketId(id);
	}
	@PutMapping("/list-seat-by-Schedule")
	public List<Seat> findSeatByScheduleId(@RequestBody Map<String, Long> map)
	{
		return schedule.findSeatByScheduleId(map.get("scheduleId"));
	}
	@GetMapping("/find-seat-booked")
	public List<String> SeatBooked(@RequestBody Map<String, String> map)
	{
		return schedule.getSeatBooked(Long.parseLong(map.get("busId")), map.get("startDate"), map.get("startTime"));
	}
	@PutMapping("/find-seat-booked-by-schedule")
	public List<Long> listSeatBooked(@RequestBody Map<String, Long> map)
	{
		List<Long> list = schedule.findSeatBookedByScheduleId(map.get("scheduleId"));
		
		return list;
	}
}
