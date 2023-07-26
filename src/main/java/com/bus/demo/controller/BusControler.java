package com.bus.demo.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.Bus;
import com.bus.demo.entity.GetInfor;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.entity.Ticket;
import com.bus.demo.entity.User;
import com.bus.demo.repo.BusRepo;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISchedule;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;

@RestController
@RequestMapping()
@CrossOrigin(value = "*")
public class BusControler {
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
//@GetMapping ("/all")
//public ResponseEntity<List<Bus>>  getAll(){
//	return new ResponseEntity<>(IBus.findAll(),HttpStatus.OK);
//}
	@PostMapping("/add")
	public String addBus(@RequestBody Bus bus)
	{
		return busRepo.saveBus(bus);
	}
	@PostMapping("update-bus/{busId}")
	public String UpdateBus(@PathVariable("busId") long busId)
	{
		return busRepo.checkUpdateBus(busId);
	}
	@GetMapping("/findAllBus")
	public List<Bus> findAllBus(){
		return busRepo.findAll();
	}
	//Update xe
	@PutMapping("/updateBus")
	public String updateBus(@RequestBody Bus bus) {
		return busRepo.update(bus);
	}
	@GetMapping("/findBus")
	public Set<Bus> findBusToCurrentDate()
	{
	return (schedule.findBusByStartDate());
	}
	
	@GetMapping("/{id}")
	public Optional<Bus> findAllSeat(@PathVariable long id)
	{
	return Optional.of(busRepo.findById(id));
	}
	@PostMapping("/delete-bus")
	public String deleteBus(@RequestBody Map<String, Long> map)
	{
		return busRepo.deletedBus((map.get("busId")));
	}
	
	
	
//	@PostMapping ("/add-ticket")
//	public String  addTicket(@RequestBody Ticket ticket) {
//		return ticketService.saveTicket(ticket)?"Add Success":"Sorry seat already booked";
//	
//	}
	
	
	
	
	
	
	
	
		
//		List<String> integeres= new ArrayList<>();
//		for(int i=0;i<=map.size()-1;i++)
//		{
//			String a=map.get(i).get(""+i+"");
//			integeres.add(a);
//		}
//		return integeres;
	}
	
	
