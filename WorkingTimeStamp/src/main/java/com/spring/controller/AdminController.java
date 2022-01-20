package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.User;
import com.spring.repository.UserRepository;

@Controller
public class AdminController {
	
	@Autowired
	private UserRepository userRepo; 
	
	@GetMapping("/admin/update/{userId}")
	public String getAdminUpdateEmployee(@PathVariable("userId") int id, Model model) {
		User employee = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user ID : " + id));
		model.addAttribute("employee", employee);
		return "update-employee2";
	}
	
	@PostMapping("/admin/update/{userId}")
	public String updateAdminMyEmployee(@PathVariable("userId") int id, @Valid User employee, 
			BindingResult result, Model model,
			@CurrentSecurityContext(expression="authentication?.name") String username) {

		int workplaceId = employee.getWorkplaceId();
		
		if (result.hasErrors()) {
			 employee.setUserId(id);			 
		 }
		 employee.setWorkplaceId(workplaceId);
		 userRepo.save(employee);
		 model.addAttribute("employees", userRepo.findByWorkplaceId(workplaceId));
		
			model.addAttribute("users", userRepo.findAll());
			return "users";
	}
}
