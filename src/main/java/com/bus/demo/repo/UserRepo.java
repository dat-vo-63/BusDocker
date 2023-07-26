package com.bus.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bus.demo.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{
public User findByPhoneNumber(String phoneNumber);
public User findByPhoneNumberAndUserName(String phone,String userName);
public User findByEmailAndPassword(String userName,String password);
public User findByEmail(String email);
}
