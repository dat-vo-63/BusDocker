package com.bus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bus.demo.entity.Bus;
@Repository
public interface BusRepo extends JpaRepository<Bus, Long> {
	public Bus findById(long id);
}
