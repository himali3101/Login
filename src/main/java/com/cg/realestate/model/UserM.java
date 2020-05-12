package com.cg.realestate.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCrypt;



@Entity
public class UserM {

	@Id  
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;
	@Column(unique=true)
	@NotNull
	@Pattern(regexp = "^[A-Za-z]*$",message = "Name must be alphabet")
	private String userName;
	@Column
	@NotNull
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$",message = "Password must of min 8char ")
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