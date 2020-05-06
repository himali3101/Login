package com.cg.realestate.model;

public class User {

	private int userId;
	private String userName;
	private String EmailId;
	private String password;
	private String phoneNo;
	private String userType;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String userName, String emailId, String password, String phoneNo, String userType) {
		super();
		this.userName = userName;
		EmailId = emailId;
		this.password = password;
		this.phoneNo = phoneNo;
		this.userType = userType;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEmailId() {
		return EmailId;
	}
	public void setEmailId(String emailId) {
		EmailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	
	
}
