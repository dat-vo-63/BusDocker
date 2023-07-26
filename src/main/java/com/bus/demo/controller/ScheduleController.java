package com.bus.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bus.demo.entity.Schedual;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISchedule;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;
@RestController
@CrossOrigin(value = "*")
public class ScheduleController {
	@Autowired
	IBus busRepo;
	@Autowired
	IBus Ibus;
	@Autowired
	ISchedule schedule;
	@Autowired
	TicketService ticketService;
	@Autowired
	ISeat iSeat;
	@Autowired
	IBill iBill;
	@Autowired
	IUser iUser;
	@PostMapping("/add/schedule")
	public Schedual addSche(@RequestBody Schedual schedual) {
		return  schedule.saveSchedual(schedual);
	}
	@GetMapping("/find-all-schedule")
	public List<Schedual> findAll(){
		return schedule.findAll();
	}
	@PutMapping("/find-schedule-start-time-depart-des")
	public List<Schedual> findScheduleByStartTimeAndDepAndDes(@RequestBody Map<String, String> map)
	{
		List<Schedual> scheduals= schedule.findByStartDateAndDepartureAndDestinations(map.get("startDate"),map.get("departure"), map.get("destinations"));
		return scheduals;
		
	}
	@GetMapping("/get-schedule-start-time")
	public Schedual getScheduleByStartTime(@RequestBody Schedual schedual) {
		return  schedule.findScheduleByStartTime(schedual.getStartTime());
	}
	@GetMapping("/get/{id}")
	public Schedual getSchedule(@PathVariable long id) {
		return schedule.findScheduleById(id);
	}
	@GetMapping("/get-schedule-bus-id-start-time-start-date/{id}")
	public List<Schedual> getScheduleByBusIdAndStartDateAndStartTime(@PathVariable long id,@RequestBody Schedual schedual) {
			return schedule.findScheduleByBusIdAndStartDateAndStartTime(id, schedual.getStartDate(), schedual.getStartTime());
	}
	@PostMapping("/update-schedule")
	public Schedual updateSchedule(@RequestBody Schedual schedual) {
		return  schedule.updateSchedule(schedual);
	}
	@GetMapping("/check-update/{id}")
	public String checkUpdate(@PathVariable("id") long id) {
		return  schedule.checkUpdate(id);
	}
	@GetMapping("/get-start-time/{id}")
	public List<String> getStartTime(@RequestBody Schedual schedual,@PathVariable long id) {
		return  schedule.getStartTime(id,schedual.getStartDate());
	}
	@PutMapping("/get-schedule-start-date")
	public List<Schedual> getAllByStartDate(@RequestBody Map<String, String> map)
	{
		List<Schedual> list= schedule.findByStartDate(map.get("startDate"));
		return list;
	}
	@PutMapping("/get-departure")
	public Set<String> getAllDepartureByStartDate(@RequestBody Map<String, String> map)
	{
		return schedule.getAllDeparture(map.get("startDate"));
	}
	@PutMapping("/get-destinations")
	public Set<String> getAllDestinationsByStartDate(@RequestBody Map<String, String> map)
	{
		return schedule.getAllDestinations(map.get("startDate"));
	}
	@GetMapping ("get-Schedule/{offset}/{pagesize}/{field}")
	public Page<Schedual> getSchedulePage(@PathVariable("offset") int offset,@PathVariable ("pagesize") int pagesize,@PathVariable("field") String field)
	{
		return schedule.findScheduleWithPaginationWithSorting(offset, pagesize,field);
	}
	@PostMapping("/delete-schedule")
	public String deleteSchedule(@RequestBody Map<String, Long> map) {
		return schedule.deleteSchedule(map.get("scheduleId"));
	}
}
