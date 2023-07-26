package com.bus.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Ticket;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;
@RestController
@CrossOrigin(value = "*")
public class TicketController {
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

	@PostMapping("/add-ticket")
	public String addTickk(@RequestBody Ticket ticket)
	{
		List<Long> list = new ArrayList<>();
		for(int i=0;i<=ticket.getSeats().size()-1;i++)
		{
		long a = Long.parseLong (ticket.getSeats().get(i).getSeatNo());
		System.out.println(a);
		list.add(a);
		}
		
		
		return ticketService.saveTicket(list);
}
}
