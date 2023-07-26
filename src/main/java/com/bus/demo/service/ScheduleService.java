package com.bus.demo.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;
import com.bus.demo.entity.SortBySeatId;
import com.bus.demo.repo.BusRepo;
import com.bus.demo.repo.ISchedule;
import com.bus.demo.repo.ScheduleRepo;
import com.bus.demo.repo.SeatRepo;

@Service
public class ScheduleService implements ISchedule{
@Autowired
ScheduleRepo repo;
@Autowired 
BusRepo busRepo;
@Autowired
SeatRepo seatRepo;
	@Override
	public boolean bookSeat(long scheduleId, List<Seat> seat) {
//		Schedual schedual = repo.findById(scheduleId);
//		boolean check = false;
//		if(schedual ==null)
//		{
//			check= false;
//		}
//		else
//		{
//			List<Seat> list = schedual.getSeats();
//			for(int i=0;i < seat.size();i++ )
//			{
//				if(list.contains(seat.get(i)))
//				{
//					check = false;
//					break;
//				}
//				else
//				{
//					list.add(seat.get(i));
//					schedual.setSeats(list);
//					schedual.setSeatLeft(schedual.getTotalSeat()-schedual.getSeats().size());
//					repo.save(schedual);
//					check = true;
//				}
//			}
//		}
//		return check;
		return true;
	}
	@Override
	public Schedual saveSchedual(Schedual schedual) {
		List<Seat> seats = new ArrayList<>();
		Bus bus = busRepo.findById(schedual.getBus().getBusId());
		
		if (bus == null)
		{
			System.out.println("null");
		}
		else {
			String timestart = null;
			String timeStart = schedual.getStartTime();
			String arr[] = timeStart.split(":");
			int h = Integer.parseInt(arr[0]);
			int m = Integer.parseInt(arr[1]);
			if(h<10)
			{
				arr[0]= "0"+arr[0];
			}
			if(m <10)
			{
				arr[1]= "0"+arr[1];
			}
			timestart = arr[0]+":"+ arr[1];
			System.out.println(timestart);
			String timeend = null;
			String timeEnd = schedual.getEndTime();
			String arrEnd[] = timeEnd.split(":");
			int hend = Integer.parseInt(arrEnd[0]);
			int mend = Integer.parseInt(arrEnd[1]);
			if(hend<10)
			{
				arrEnd[0]= "0"+arrEnd[0];
			}
			if(mend <10)
			{
				arrEnd[1]= "0"+arrEnd[1];
			}
			timeend = arrEnd[0]+":"+ arrEnd[1];
			
			String startdate = null;
			String startDate = schedual.getStartDate();
			String arrdate[]= startDate.split("/");
			int day= Integer.parseInt(arrdate[2]);
			int month = Integer.parseInt(arrdate[1]);
			
			if(day<10)
			{
				arrdate[2] = "0"+arrdate[2];
			}
			if(month < 10)
			{
				arrdate[1] = "0"+arrdate[1];
			}
			startDate = arrdate[0]+"/"+arrdate[1]+"/"+arrdate[2];
			
			System.out.println(timeend);
			System.out.println(startDate);
			schedual.setStartDate(startDate);
			schedual.setStartTime(timestart);
			schedual.setEndTime(timeend);
			schedual.setTotalSeat(bus.getSeat());
			schedual.setSeatLeft(bus.getSeat());
		for(int i=0;i<=schedual.getTotalSeat()-1;i++)
		{
			Seat seat = new Seat();
			seat.setSeatNo(Integer.toString(i+1));
			seat.setSchedual(schedual);
			if(i >= 90)
			{
				seat.setSeatPrice(90000);
			}
			else
			{
				seat.setSeatPrice(50000);
			}
			seatRepo.save(seat);
			seats.add(seat);
		}
		schedual.setSeats(seats);
		schedual.setBus(bus);
		}
		
		return repo.save(schedual);
	}
	@Override
	public Schedual findScheduleByStartTime(String starttime) {
		Schedual list= repo.getSchedualsByStartTime(starttime);
		
			
		return list;
	}
	@Override
	public Schedual updateSchedule(Schedual schedual) {
		Schedual schedual2 = null; 
			schedual2 =	repo.findByScheduleId(schedual.getScheduleId());
			
			if (schedual2 != null && schedual2.getSeatLeft() == schedual2.getBus().getSeat())
			{
				String arr[] = schedual.getStartDate().split("/");
				int day = Integer.parseInt(arr[2]);
				int month= Integer.parseInt(arr[1]);
				if(day<10)
				{
					arr[2]= "0"+arr[2];
				}
				if(month<10)
				{
					arr[1]= "0" +arr[1];
				}
				//Tranfers
				String schedualStartDate = arr[0]+"/"+arr[1]+"/"+arr[2];
				//-------------------------------------------
				String timeStart = schedual.getStartTime();
				String arr1[] = timeStart.split(":");
				int h = Integer.parseInt(arr1[0]);
				int m = Integer.parseInt(arr1[1]);
				if(h<10)
				{
					arr1[0]= "0"+arr1[0];
				}
				if(m <10)
				{
					arr1[1]= "0"+arr1[1];
				}
				timeStart = arr1[0]+":"+ arr1[1];
				//---------------------------------------------------------------
				String timeEnd = schedual.getEndTime();
				String arr2[] = timeEnd.split(":");
				int he = Integer.parseInt(arr2[0]);
				int me = Integer.parseInt(arr2[1]);
				if(he<10)
				{
					arr2[0]= "0"+arr2[0];
				}
				if(me <10)
				{
					arr2[1]= "0"+arr2[1];
				}
				timeEnd = arr2[0]+":"+ arr2[1];

				schedual2.setStartDate(schedualStartDate);
				schedual2.setStartTime(timeStart);
				schedual2.setEndTime(timeEnd);
				schedual2.setDeparture(schedual.getDeparture());
				schedual2.setDestinations(schedual.getDestinations());
				repo.save(schedual2);
			}
		return schedual2;
	}
	@Override
	public List<String> getStartTime(long busId, String startDate) {
		Date  currentDate = new Date();
		System.out.println(currentDate);
		LocalDateTime dateTime2 = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDate dateTimeGetDate = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateTimeGetDate);
		System.out.println(dateTime2.format(DateTimeFormatter.ofPattern("HH.mm")));
		double check = Double.parseDouble(dateTime2.format(DateTimeFormatter.ofPattern("HH.mm")));
		List<String> list= repo.getStartTimeByBusIdAndStartDate(busId, startDate);
		List<String> newList=new ArrayList<>();
		for(int i=0;i<=list.size()-1;i++) {
			String a= list.get(i).replace(':', '.');
			//compare laf  time date cua schedule
			double compare = Double.parseDouble(a) ;
			if(check<compare)
			{
				newList.add(list.get(i));
			}
		}
		return newList;
	}
	@Override
	public List<Schedual> findScheduleByBusIdAndStartDateAndStartTime(long busId, String startDate, String startTime) {
		
		return repo.findByBusIdAndStartDateAndStartTime(busId, startDate, startTime);
	}
	@Override
	public Schedual findScheduleById(long id) {
		
		return repo.findByScheduleId(id);
	}
	@Override
	public Set<Bus> findBusByStartDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		String a = dateFormat.format(date);
		System.out.println(a);
		List<Schedual> scheduals= repo.findByStartDate(a);
		Set<Bus> bus = new HashSet<>();
		for(int i=0;i<scheduals.size();i++) {
			bus.add(scheduals.get(i).getBus());
		}
		return bus;
	}
	@Override
	public List<String> getSeatBooked(long busId,String startDate,String startTime) {
		List<Schedual> scheduals = findScheduleByBusIdAndStartDateAndStartTime(busId, startDate, startTime);
		List<Seat> seats = scheduals.get(0).getSeats();
		List<Seat> seats2 = new ArrayList<>();
		List<String> list = new ArrayList<>();
		for(int i =0;i<=seats.size()-1;i++) {
			if(seats.get(i).getTicket()!=null)
			{
				seats2.add(seats.get(i));
			}
		}
		for(int j=0;j<=seats2.size()-1;j++) {
			list.add(seats.get(j).getSeatNo());
		}
		return list;
	}
	@Override
	public List<Schedual> findbyBusId(long busId) {
		
		return repo.findByBus(busId);
	}
	@Override
	public String checkUpdate(long scheduleId) {
		Schedual schedual = repo.findByScheduleId(scheduleId);
		int check =0;
		for(int i =0;i<schedual.getSeats().size();i++)
		{
			if(schedual.getSeats().get(i).getTicket()!=null) {
				check++;
				break;
			}
		}
		if(check >0)
		{
			return "Sorry Schedule Has Ticket So You Can't Update";
		}
		else {
			return "You can Update";
		}
	}
	@Override
	public List<Schedual> findAll() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}
	@Override
	public List<Schedual> findByStartDateAndDepartureAndDestinations(String startDate, String Depart, String Des) {
		// TODO Auto-generated method stub
		String arr[] = startDate.split("/");
		int day= Integer.parseInt(arr[2]);
		int month = Integer.parseInt(arr[1]);
		if(day<10)
		{
			arr[2]= "0"+arr[2];
		}
		if(month<10)
		{
			arr[1]="0"+arr[1];
		}
		startDate = arr[0]+"/"+arr[1]+"/"+arr[2];
		return repo.findByStartDateAndDepartureAndDestinations(startDate, Depart, Des);
	}
	@Override
	public Set<String> getAllDeparture(String date) {
		Set<String> departure= null;
//		String arr[] = date.split("/");
//		int day= Integer.parseInt(arr[2]);
//		int month = Integer.parseInt(arr[1]);
//		if(day<10)
//		{
//			arr[2]= "0"+arr[2];
//		}
//		if(month<10)
//		{
//			arr[1]="0"+arr[1];
//		}
//		date = arr[0]+"/"+arr[1]+"/"+arr[2];
		List<Schedual> list = findByStartDate(date);
		if(list.isEmpty()==false)
		{
		departure= new HashSet<>();
		for(int i =0;i<=list.size()-1;i++)
		{
		departure.add(list.get(i).getDeparture());
		}
		}
		
		
		return departure;
	}
	@Override
	public Set<String> getAllDestinations(String date) {
		
		List<Schedual> list = findByStartDate(date);
		Set<String> destinations= null;
		if(list.isEmpty()==false)
		{
		
			destinations= new HashSet<>();
		for(int i =0;i<=list.size()-1;i++)
		{
		destinations.add(list.get(i).getDestinations());
		}
		
		}
		return destinations;
	}
	@Override
	public List<Schedual> findByStartDate(String date) {
		List<Schedual> listSchedule = new ArrayList<>();
		List <Schedual> result = new ArrayList<>();
		String arr[] = date.split("/");
		int day = Integer.parseInt(arr[2]);
		int month= Integer.parseInt(arr[1]);
		if(day<10)
		{
			arr[2]= "0"+arr[2];
		}
		if(month<10)
		{
			arr[1]= "0" +arr[1];
		}
		date = arr[0]+"/"+arr[1]+"/"+arr[2];
		System.out.println(date);
		date = date.replace('/', '-');
		Date  currentDate = new Date();
		System.out.println(currentDate);
		LocalDateTime dateTime = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDate dateTimeGetDate = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateTimeGetDate);
		LocalDate dateSchedule = LocalDate.parse(date);
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	    String formattedDate = formatter.format(dateSchedule);
	    System.out.println("Formatted date: " + formattedDate);
	    dateSchedule = LocalDate.parse(formattedDate);
	    double currentTime = Double.parseDouble(dateTime.format(DateTimeFormatter.ofPattern("HH.mm")));
		if(dateTimeGetDate.isBefore(dateSchedule) || dateTimeGetDate.isEqual(dateSchedule))
		{
			date = date.replace('-', '/');
			listSchedule= repo.findByStartDate(date);
		for(int i=0;i<=listSchedule.size()-1;i++)
		{
			if(dateTimeGetDate.isBefore(dateSchedule))
			{
				result.add(listSchedule.get(i));
			}
			else {
				String scheduleTime = listSchedule.get(i).getStartTime();
				scheduleTime = scheduleTime.replace(':', '.');
				double scheduleTimeConvert = Double.parseDouble(scheduleTime);
				if(currentTime<scheduleTimeConvert)
				{
					result.add(listSchedule.get(i));
				}
			}
			
		}
		}
		return result;
		
	}
	@Override
	public List<Long> findSeatBookedByScheduleId(long scheduleId) {
		List<Long> seatIds = new ArrayList<>();
		Schedual schedual = repo.findByScheduleId(scheduleId);
		if(schedual!= null) {
			for(int i =0;i<=schedual.getSeats().size()-1;i++) {
				if(schedual.getSeats().get(i).getTicket()!=null)
				{
					seatIds.add(schedual.getSeats().get(i).getSeatId());
					Collections.sort(schedual.getSeats(),new SortBySeatId());
				}
			}
			
		
			
		return seatIds;
		}
		return null;
	}
	@Override
	public List<Seat> findSeatByScheduleId(long scheduleId) {
		Schedual schedual = repo.findByScheduleId(scheduleId);
		List<Seat> seats = schedual.getSeats();
		Collections.sort(seats);
		return seats;
	}
	@Override
	public List<Schedual> findScheduleWithSort(String field) {
		// TODO Auto-generated method stub
		return repo.findAll(Sort.by(Sort.Direction.ASC,field));
	}
	@Override
	public Page<Schedual> findScheduleWithPaginationWithSorting(int offset, int pageSize,String field) {
		Page<Schedual> page = repo.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(field)));
		return page;
	}
	@Override
	public String deleteSchedule(long schedule) {
		Date  currentDate = new Date();
		System.out.println(currentDate);
		LocalDateTime dateTime = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault())
			      .toLocalDateTime();
		LocalDate dateTimeGetDate = Instant.ofEpochMilli(currentDate.getTime())
			      .atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateTimeGetDate);
		double currentTime = Double.parseDouble(dateTime.format(DateTimeFormatter.ofPattern("HH.mm")));
		Schedual schedual = repo.findByScheduleId(schedule);
		if(schedual!= null)
		{
			String schedualstartDate = schedual.getStartDate();
			schedualstartDate = schedualstartDate.replace('/', '-');
			LocalDate date = LocalDate.parse(schedualstartDate);
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    String formattedDate = formatter.format(date);
		    System.out.println("Formatted date: " + formattedDate);
		    date = LocalDate.parse(formattedDate);
		    String scheduleTime = schedual.getStartTime();
		    scheduleTime = scheduleTime.replace(':', '.');
		    double scheduleTimeConvert = Double.parseDouble(scheduleTime);  
		    if(dateTimeGetDate.isBefore(date) || dateTimeGetDate.isEqual(date) && currentTime < scheduleTimeConvert)
		    {
		    	repo.deleteById(schedule);
		    	return "Deleted";
		    }
		    else  {
				return "Can't Deleted";
			}
		}
		return "Can't found Schedule";
	}
	

}
