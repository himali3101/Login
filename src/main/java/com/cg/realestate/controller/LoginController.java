package com.cg.realestate.controller;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.cg.realestate.model.User;
import com.cg.realestate.service.LoginService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(value="login")
@RequestMapping(path = "/login")
public class LoginController {


	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Autowired
	LoginService service;
	
	@PostMapping(path = "/signup")
	@ApiOperation(value = "sign Up", nickname = "Sign Up")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User.class),
							@ApiResponse(code = 500, message = "Failure", response = User.class) })
	public User singUp(@RequestBody User user) {
		return service.addUser(user);
	}
	
	@GetMapping(path = "/{userName}/{password}")
	@ApiOperation(value = "login", nickname = "login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User.class),
							@ApiResponse(code = 500, message = "Failure", response = User.class) })
	public boolean login(@PathVariable String userName, @PathVariable String password) {
		
		List<User> users = service.findByName(userName);
		for (User user : users) {
			if (user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}
	
/*	@Autowired
	LoginProxy proxy;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping(path = "/login/{emailId}/{password}")
	@HystrixCommand(fallbackMethod="alternateMethod")
	@ApiOperation(value = "login", nickname = "login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = User.class),
							@ApiResponse(code = 500, message = "Failure", response = User.class) })
	public boolean login(@PathVariable String emailId, @PathVariable String password) {

		// List<User> users;// = proxy.display();

		logger.info("log-in");
		logger.trace(" Inside login() function");
		logger.error("Error happened at login()");

		String url = "http://localhost:9003/";
		RestTemplate restTemplate = new RestTemplate();
		// List<User> user = restTemplate.getClientHttpRequestInitializers(url)
		ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(url, User[].class);
		List<User> users = Arrays.asList(responseEntity.getBody());

		for (User user : users) {

			if (user.getUserName()().equals(emailId) && user.getPassword().equals(password)) {
				System.out.println("inside controller");
				return true;
			}

		}
		return false;
	}
	
	public boolean alternateMethod() {   
        logger.info("Due to Exception, the fallbackmethod is called by Hystrix");
        return false;
 }*/
		
	
}
