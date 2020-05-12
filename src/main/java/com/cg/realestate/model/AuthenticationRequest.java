package com.cg.realestate.model;

// this model is jsut to make it easy for the Authentication api to parse incoming credentials
// this also defines the field names that should be passed through json

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