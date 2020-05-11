package com.cg.realestate.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.model.User;



@Repository
public interface LoginDao extends JpaRepository<User, Integer>{

	public List<User> findByUserName(String userName);
	
	//public User findByEmailId(String emailId);
	
}