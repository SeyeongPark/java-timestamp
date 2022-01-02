package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.User;
import com.spring.repository.UserRepository;


@Controller
public class ManagerController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/manager/register")
	public String getRegisterManager(User user) {
		return "add-manager";
	}
	
	@PostMapping("/manager/register")
	public String registerManager(@Valid User user,  BindingResult result, Model model) {
		 
		if(result.hasErrors())
		  {
			return "add-manager";
		  }
		if(userRepo.findByEmail(user.getEmail()) != null) {
			String exist_email = "Exist email !";
			model.addAttribute("exist_email", exist_email);
			return "add-manager";
		}
		
		// encode entered password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setPosition("manager");
		// save data into Manager DB table
		userRepo.save(user);
		return "home";
	}
	
	@GetMapping("/manager/employees")
	public String checkEmployees(Model model, @CurrentSecurityContext(expression="authentication?.name") String username) {
		
		User currentUser = userRepo.findByEmail(username);
		int workplaceId = currentUser.getWorkplaceId();
		
		// Employee cannot access this page
		if(currentUser.getPosition().contains("employee")){
			return "home";
		}
		else {
			model.addAttribute("employees", userRepo.findByWorkplaceId(workplaceId));
			return "my-employees";
		}
	}
}
