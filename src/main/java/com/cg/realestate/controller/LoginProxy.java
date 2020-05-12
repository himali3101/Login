package com.cg.realestate.controller;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.cg.realestate.model.UserM;

@FeignClient(name="create-or-update-profile")
@RibbonClient(name="create-or-update-profile")
public interface LoginProxy {

//	@GetMapping(path = "/login/{emailId}")
	//public User findByEmailId(@PathVariable String emailId);

	@GetMapping(path = "/")
	public List<UserM> display();

}