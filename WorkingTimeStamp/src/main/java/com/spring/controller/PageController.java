package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.entity.User;
import com.spring.repository.UserRepository;
import com.spring.repository.TimeStampRepository;

@Controller
public class PageController {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TimeStampRepository timeRepo;
		
	@GetMapping("/")
	public String getHomePage(@CurrentSecurityContext(expression="authentication?.name")
    String username, Model model) {
		User currentUser = userRepo.findByEmail(username);
		String name = currentUser.getName();
		int userWorkplaceId = currentUser.getWorkplaceId();
		model.addAttribute("name", name);
		model.addAttribute("timestamps", timeRepo.findByWorkplaceIdOrderByStartTimeDesc(userWorkplaceId));

		return "home";
	}
}
