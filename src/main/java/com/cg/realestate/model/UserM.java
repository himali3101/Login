package com.cg.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCrypt;



@Entity
public class UserM {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(unique=true)
	private String userName;
	@Column
	private String password;
	@Column
	private String userType;
	
	public UserM() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserM(String userName, String password,String userType) {
		super();
		this.userName = userName;
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	
	
}