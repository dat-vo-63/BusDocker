package com.bus.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.bus.demo.dto.util.EmailUtil;
//import com.bus.demo.dto.util.OtpUtil;
import com.bus.demo.entity.User;
import com.bus.demo.repo.IUser;
import com.bus.demo.repo.UserRepo;

@Service
public class UserService  implements IUser{
@Autowired
UserRepo repo;
//@Autowired
// OtpUtil otpUtil;
//@Autowired
// EmailUtil emailUtil;
	@Override
	public User findByPhoneNumber(String phoneNumber) {
		
		return repo.findByPhoneNumber(phoneNumber);
	}

	@Override
	public String save(User user) {
		String check = "";
		User user2 = repo.findByPhoneNumber(user.getPhoneNumber());
		User user3 = repo.findByEmail(user.getEmail());
		if(user2== null&& user3 ==null)
		{
		user.setIsActive("active");
		repo.save(user);
		return "Register Successful";
		}
		else if (user3!=null && user2==null)  {
			return check+"Phone Have Exits";
		}
		else if (user3== null && user2 !=null) {
			return check+"Email Have Exits";
		
		}
		else {
			return check+" Phone and Email Have Exits";
		}
		
	}

	@Override
	public User login(String email, String password) {
		User user = repo.findByEmailAndPassword(email, password);
		if(user==null)
		{
			return user; 
		}
		else {
			return user;
		}
		 
	}

	@Override
	public String updateUser(User user) {
		User user2 = repo.findByEmail(user.getEmail());
		if(user.getAddress()!=null)
		{
			user2.setAddress(user.getAddress());
		}
		if(user.getPassword()!= null) {
			user2.setPassword(user.getPassword());
			
		}
		if(user.getPhoneNumber()!=null)
		{
			User user3 = repo.findByPhoneNumber(user.getPhoneNumber());
			if (user3!=null&& user3.getEmail()!=user2.getEmail()) {
				return "Phone Number Is Already Exited";
			}
			else {
				user2.setPhoneNumber(user.getPhoneNumber());
			}
		}
		if(user.getUserName()!=null) {
			user2.setUserName(user.getUserName());
		}
		repo.save(user2);
		return "Update Success";
	}

	@Override
	public User findByEmail(String email) {
		
		return repo.findByEmail(email);
	}

}
