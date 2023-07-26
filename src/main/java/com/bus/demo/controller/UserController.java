package com.bus.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.User;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;
@RestController
@CrossOrigin(value = "*")
public class UserController {
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
	@PostMapping ("/add-user")
	public String addUser(@RequestBody User user) {
		return iUser.save(user);
	}
	
	@PostMapping("/login")
	public User login(@RequestBody User user) {
		return iUser.login(user.getEmail(), user.getPassword());
	}
	@PostMapping("/update-user")
	public String Update(@RequestBody User user)
	{
		return iUser.updateUser(user);
	}
	@GetMapping("/find-By-Email/{email}")
	public User findByEmail(@PathVariable ("email")  String email) {
		return iUser.findByEmail(email);
	}
}
