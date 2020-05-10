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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.cg.realestate.model.User;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(value="login")
public class LoginController {

	@Autowired
	LoginProxy proxy;

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@GetMapping(path = "/login/{emailId}/{password}")
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

			if (user.getEmailId().equals(emailId) && user.getPassword().equals(password)) {
				System.out.println("inside controller");
				return true;
			}

		}
		return false;
	}
}
