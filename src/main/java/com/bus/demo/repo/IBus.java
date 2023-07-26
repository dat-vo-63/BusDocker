package com.bus.demo.repo;

import java.util.List;

import com.bus.demo.entity.Bus;
import com.bus.demo.entity.Seat;

public interface IBus {
public boolean bookSeat(long id,List<Seat> list);
public String  saveBus(Bus bus);
public Bus findById(long id);
public List<Bus> findAll();
public String checkUpdateBus(long busId);
public String update(Bus bus);
public String deletedBus(long busId);
}
