package com.bus.demo.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.repo.BusRepo;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISchedule;
@Service
public class BusImp implements IBus {
	@Autowired
	BusRepo busRepo;
	@Autowired
	ISchedule iSchedule;

	@Override
	public boolean bookSeat(long id, List<Seat> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String saveBus(Bus bus) {
		
		return busRepo.save(bus) != null?"Add Susscess":"Error";
	}

	@Override
	public Bus findById(long id) {
		// TODO Auto-generated method stub
		return busRepo.findById(id);
	}

	@Override
	public List<Bus> findAll() {
	
		return busRepo.findAll();
	}

	@Override
	public String checkUpdateBus(long busId) {
		List<Schedual> scheduals = iSchedule.findbyBusId(busId);
		
		int check =0;
		Date  currentDate = new Date();
		System.out.println(currentDate);
		LocalDateTime dateTime = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDate Date = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault()).toLocalDate();
		double currentTime = Double.parseDouble(dateTime.format(DateTimeFormatter.ofPattern("HH.mm")));
		for(int i=0;i<=scheduals.size()-1;i++)
		{
			String getscheduleTime = scheduals.get(i).getStartTime();
			getscheduleTime = getscheduleTime.replace(':', '.');
			double scheduleTime = Double.parseDouble(getscheduleTime);
			String dateSchedule = scheduals.get(i).getStartDate().replace('/', '-');
			System.out.println(dateSchedule);
			 LocalDate date = LocalDate.parse(dateSchedule);
			    if(Date.isBefore(date) || Date.isEqual(date) && currentTime < scheduleTime )
			    {
			    	check++;
			    	break;
			    }
			
		}
		if(check>0)
		{
			return "Can't Update Bus because Schedule Already Create";
		}
		else {
			return "You can Update";
		}
	}

	@Override
	public String update(Bus bus) {
		String check = checkUpdateBus(bus.getBusId());
		if(check.equalsIgnoreCase("You can Update"))
		{
		 Bus bu= busRepo.findById(bus.getBusId());
		 if(bu!=null) {
			 bu.setName(bus.getName());
			 bu.setSeat(bus.getSeat());
			 busRepo.save(bu);
			 return "Update Success";
		 }
		 else {
			return "Some Ínfomation Is Not Correct";
		}
		}
		else {
			return "Can't Update";
		}
	}

	@Override
	public String deletedBus(long busId) {
		Bus bus = busRepo.findById(busId);
		List<Schedual> list =(iSchedule.findbyBusId(busId));
		if(list.isEmpty())
		{
			busRepo.delete(bus);
			return "Deleted Success";
		}
		else {
			return "Can't Deleted";
		}
		
	}

//	@Override
//	public boolean bookSeat(long id, List<Seat> list) {
//		Bus bus = busRepo.getById(id);
//		boolean check = false;
//		if (bus == null) {
//			check = false;
//		} else {
//
//			List<Seat> seats = bus.getSeats();
//			for (int i = 0; i < list.size(); i++) {
//				if (seats.contains(list.get(i))) {
//					check = false;
//					break;
//				} else {
//					seats.add(list.get(i));
//					bus.setSeats(seats);
//					busRepo.save(bus);
//					check = true;
//				}
//			}
//		}
//		return check;
//	}

}
