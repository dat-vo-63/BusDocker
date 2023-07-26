package com.bus.demo.repo;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Schedual;
import com.bus.demo.entity.Seat;

public interface ISchedule {
 public boolean bookSeat(long scheduleId,List<Seat> seat);
 public Schedual saveSchedual(Schedual schedual);
 public Schedual findScheduleByStartTime(String starttime);
 public Schedual findScheduleById(long id);
 public Schedual updateSchedule(Schedual schedual);
 public List<String> getStartTime(long busId,String startDate);
 public List<Schedual> findScheduleByBusIdAndStartDateAndStartTime(long busId,String startDate,String startTime);
 public Set<Bus> findBusByStartDate();
 public List<String> getSeatBooked(long busId,String startDate,String startTime);
 public List<Schedual> findbyBusId(long busId);
 public String checkUpdate(long scheduleId);
 public List<Schedual> findAll();
 public List<Schedual> findByStartDateAndDepartureAndDestinations(String startDate,String Depart,String Des);
 public Set<String> getAllDeparture(String date);
 public Set<String> getAllDestinations(String date);
public List<Schedual> findByStartDate(String date);
public List<Long> findSeatBookedByScheduleId(long scheduleId);
public List<Seat> findSeatByScheduleId(long scheduleId);
public List<Schedual> findScheduleWithSort(String field);
public Page<Schedual> findScheduleWithPaginationWithSorting(int offset,int pageSize,String field);
public String deleteSchedule(long schedule);
}
