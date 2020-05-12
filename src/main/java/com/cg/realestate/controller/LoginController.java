package com.cg.realestate.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.realestate.model.AuthenticationRequest;
import com.cg.realestate.model.AuthenticationResponse;
import com.cg.realestate.model.UserM;
import com.cg.realestate.service.LoginService;
import com.cg.realestate.utils.JwtUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@Api(value = "login")
@RequestMapping("/login")
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@Autowired
	LoginService service;

	@PostMapping(path = "/signup")
	@ApiOperation(value = "sign Up", nickname = "Sign Up")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserM.class),
			@ApiResponse(code = 500, message = "Failure", response = UserM.class) })
	public String singUp(@RequestBody UserM user) {
		service.addUser(user);
		return "{\r\n" + 
				"		\"msg\":\"User Added Successfully\"\r\n" + 
				"	}";   
	}
	
	

	@PostMapping(path = "/authenticate")
	@ApiOperation(value = "login", nickname = "login")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = UserM.class),
			@ApiResponse(code = 500, message = "Failure", response = UserM.class) })
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		
		final UserM user = service.findByName(authenticationRequest.getUsername()).get(0);
		
		
		// using the jwt utils library to generate jwt
		final String jwt = jwtTokenUtil.generateToken(user.getUserName(),user.getUserType());
		// Modified to send in user rather than userDetails

		// returning jwt as json
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@GetMapping("/authenticate")
	public String in() {
		return "GET + autehntication";
	}
			
	}

	/*
	 * @Autowired LoginProxy proxy;
	 * 
	 * private static final Logger logger =
	 * LoggerFactory.getLogger(LoginController.class);
	 * 
	 * @GetMapping(path = "/login/{emailId}/{password}")
	 * 
	 * @HystrixCommand(fallbackMethod="alternateMethod")
	 * 
	 * @ApiOperation(value = "login", nickname = "login")
	 * 
	 * @ApiResponses(value = { @ApiResponse(code = 200, message = "Success",
	 * response = User.class),
	 * 
	 * @ApiResponse(code = 500, message = "Failure", response = User.class) })
	 * public boolean login(@PathVariable String emailId, @PathVariable String
	 * password) {
	 * 
	 * // List<User> users;// = proxy.display();
	 * 
	 * logger.info("log-in"); logger.trace(" Inside login() function");
	 * logger.error("Error happened at login()");
	 * 
	 * String url = "http://localhost:9003/"; RestTemplate restTemplate = new
	 * RestTemplate(); // List<User> user =
	 * restTemplate.getClientHttpRequestInitializers(url) ResponseEntity<User[]>
	 * responseEntity = restTemplate.getForEntity(url, User[].class); List<User>
	 * users = Arrays.asList(responseEntity.getBody());
	 * 
	 * for (User user : users) {
	 * 
	 * if (user.getUserName()().equals(emailId) &&
	 * user.getPassword().equals(password)) {
	 * System.out.println("inside controller"); return true; }
	 * 
	 * } return false; }
	 * 
	 * public boolean alternateMethod() {
	 * logger.info("Due to Exception, the fallbackmethod is called by Hystrix");
	 * return false; }
	 */
