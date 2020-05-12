package com.cg.realestate.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.realestate.model.UserM;



@Repository
public interface LoginDao extends JpaRepository<UserM, Integer>{

	public List<UserM> findByUserName(String userName);
	
	//public User findByEmailId(String emailId);
	
}