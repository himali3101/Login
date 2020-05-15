package com.cg.realestate.model;



public class AuthenticationRequest {

	private String userName;
	private String password;

	public String getUsername() {
		return userName;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// need default constructor for JSON Parsing
	public AuthenticationRequest() {

	}

	public AuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

}