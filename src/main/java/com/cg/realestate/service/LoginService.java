package com.cg.realestate.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.realestate.dao.LoginDao;
import com.cg.realestate.model.UserM;

@Service
public class LoginService {
	
	@Autowired
	LoginDao dao;

	public UserM addUser(UserM user) {
		return dao.save(user);
	}
	
	public List<UserM> displayUser(){
		List<UserM> users = new ArrayList<>();
		 dao.findAll().forEach(users::add);
		 return users;
	}
	
	public List<UserM> findByName(String userName) {
		return dao.findByUserName(userName);
	}
}