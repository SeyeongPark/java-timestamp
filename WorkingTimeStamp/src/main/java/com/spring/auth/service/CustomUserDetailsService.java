package com.spring.auth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.spring.entity.*;
import com.spring.repository.ManagerRepository;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private ManagerRepository managerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Manager manager = managerRepository.findByEmail(username);
		return new CustomUserDetails(manager);
	}

}
