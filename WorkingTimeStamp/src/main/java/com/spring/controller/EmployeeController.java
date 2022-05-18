package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.User;
import com.spring.repository.UserRepository;

@Controller
public class EmployeeController {

	@Autowired
	private UserRepository userRepo; 
	
	@GetMapping("/employee/register")
	public String getRegisterEmployee(User user) {
		return "add-employee";
	}
	
	@PostMapping("/employee/register")
	public String registerEmployee(@Valid User user, BindingResult result, Model model) {
		
		if(result.hasErrors())
		  {
			return "add-employee";
		  }
		
		if(userRepo.findByEmail(user.getEmail()) != null) {
			String exist_email = "An account already exist with this email.";
			model.addAttribute("exist_email", exist_email);
			return "add-employee";
		}
		else {
			// encode entered password
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodedPassword);
			user.setPosition("employee");
			user.setSalary(0);

			// save data into Manager DB table
			userRepo.save(user);			
			return "login";
		}
	}
}
