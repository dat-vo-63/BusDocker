package com.bus.demo.repo;

import java.util.List;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.GetInfor;

public interface IBill {
	public Bill addBill(String phone,long ticketId);
	public String Pay(long billId);
	public String CounterDown(long billId);
	public Bill findById(long billId);
	public String delete(long billId) ;
	public List<GetInfor> getDetailBill();
	public GetInfor getDetailBillId(long billId);
	public List<GetInfor> findByBillIdlike(String billId);
	public List<GetInfor> findBillByEmail(String email);
	public List<GetInfor> findUserBillIdLike(String email,String billId);
	public List<GetInfor> findUserBillByEmailAndStartDate(String email,String startDate);
	public List<GetInfor> findAllBillByStartDate(String startDate);
	public List<GetInfor> getDetailBill(int offset,int pagesize);
	
	}
