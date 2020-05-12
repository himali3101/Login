package com.cg.realestate.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cg.realestate.dao.LoginDao;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	LoginDao repo;
	
	UserM user = new UserM();
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		user = repo.findByUserName(username).get(0);

		List<GrantedAuthority> roleList = new ArrayList<GrantedAuthority>();
		roleList.add(new SimpleGrantedAuthority(user.getUserType()));
		
		return new User(
				user.getUserName(), 
				user.getPassword(), 
				roleList);
	}
	

}