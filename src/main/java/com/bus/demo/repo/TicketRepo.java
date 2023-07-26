package com.bus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.bus.demo.entity.Ticket;

public interface TicketRepo extends JpaRepository<Ticket, Long>  {
public Ticket findById(long id);
@Transactional
@Modifying(clearAutomatically = true)
@Query("DELETE FROM Ticket  where ticketId =:ticketId")
public int deleteByBillId(@Param ("ticketId") long ticketId);
@Query("SELECT DISTINCT t FROM Ticket t where t.bill.billId=:billId")
public Ticket findByBillId(@Param("billId") long billId);
}

