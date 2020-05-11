package com.cg.realestate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.realestate.dao.LoginDao;
import com.cg.realestate.model.User;

@Service
public class LoginService {
	
	@Autowired
	LoginDao dao;

	public User addUser(User user) {
		return dao.save(user);
	}
	
	public List<User> displayUser(){
		List<User> users = new ArrayList<>();
		 dao.findAll().forEach(users::add);
		 return users;
	}
	
	public List<User> findByName(String userName) {
		return dao.findByUserName(userName);
	}
}
