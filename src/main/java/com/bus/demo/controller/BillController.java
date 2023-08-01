package com.bus.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.bus.demo.entity.Bill;
import com.bus.demo.entity.GetInfor;
import com.bus.demo.repo.IBill;
import com.bus.demo.repo.IBus;
import com.bus.demo.repo.ISeat;
import com.bus.demo.repo.IUser;
import com.bus.demo.service.ScheduleService;
import com.bus.demo.service.TicketService;
@CrossOrigin(origins ="*")
public class BillController {
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
	@PostMapping("/addBill")
	public Bill addBill(@RequestBody Map<String, String> input)
	{
		
		return iBill.addBill((input.get("phoneNumber")),Long.parseLong(input.get("ticketId")));
	}
	@PostMapping("/count-down")
	public String countdown(@RequestBody Map<String, Long> map)
	{
		return iBill.CounterDown(map.get("billId"));
	}
	@GetMapping("/find-bill/{id}")
	public Bill findBillById(@PathVariable long id)
	{
		return iBill.findById(id);
	}
	@DeleteMapping("/delete")
	public String deleted(@RequestBody Bill bill)
	{
		return iBill.delete(bill.getBillId());
	}
	@PostMapping("/payment/{billId}")
	public String payment(@PathVariable Long billId) {
		return iBill.Pay(billId);
	}
	//Show all bills
	@GetMapping("/find-Bill")
	public List<GetInfor> findInfo() {
		return iBill.getDetailBill();
	}
//	@GetMapping("/find-Bill-paging")
//	public APIResponse<List<GetInfor>> findInfo(@RequestBody Map<String, String>map) {
//		List<GetInfor> getInfors= iBill.getDetailBill(Integer.parseInt(map.get("offset")),5);
//		int total = iBill.getDetailBill().size();
//		return new APIResponse<>((int)Math.round((double)total/5),getInfors);
//	}
	//Show 1 bill
	@GetMapping("/get-detail-bill/{billId}")
	public GetInfor findDetailBybillId(@PathVariable("billId") long billId)
	{
		return iBill.getDetailBillId(billId);
	}//Show all bill with search
	@PutMapping("/get-bill")
	public List<GetInfor> findBill(@RequestBody Map<String, String> map){
		return iBill.findByBillIdlike((map.get("billId")));
	}
	@PutMapping("/find-bill-by-email")
	public List<GetInfor> findBillbyEmail(@RequestBody Map<String, String> map)
	{
		return iBill.findBillByEmail(map.get("email"));
	}
	@PutMapping("/find-user-bill-by-bill-id")
	public List<GetInfor> findUserBillbyEmailAndBillId(@RequestBody Map<String, String> map)
	{
		return iBill.findUserBillIdLike(map.get("email"), map.get("billId"));
	}
	@PutMapping("/find-all-bill-by-start-date")
	public List<GetInfor> findAllBillByStartDate(@RequestBody Map<String, String> map)
	{
		return iBill.findAllBillByStartDate(map.get("startDate"));
	}
	@PutMapping("/find-user-bill-by-email-start-date")
	public List<GetInfor> findAllBillByEmailAndStartDate(@RequestBody Map<String, String> map)
	{
		return iBill.findUserBillByEmailAndStartDate(map.get("email"),map.get("startDate"));
	}
}
