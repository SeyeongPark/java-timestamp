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
	
	@GetMapping("/manager/employee/update/{userId}")
	public String getUpdateEmployee(@PathVariable("userId") int id, Model model) {
		User employee = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user ID : " + id));
		model.addAttribute("employee", employee);
		return "update-employee";
	}
	
	@PostMapping("/manager/employee/update/{userId}")
	public String updateMyEmployee(@PathVariable("userId") int id, @Valid User employee, 
			BindingResult result, Model model,
			@CurrentSecurityContext(expression="authentication?.name") String username) {
		
		User currentUser = userRepo.findByEmail(username);
		int workplaceId = currentUser.getWorkplaceId();
		
		if (result.hasErrors()) {
			 employee.setUserId(id);			 
		 }
		 employee.setPosition("employee");
		 employee.setWorkplaceId(employee.getWorkplaceId());
		 userRepo.save(employee);
		 model.addAttribute("employees", userRepo.findByWorkplaceId(workplaceId));
		
		return "my-employees";
	}
	
	@PostMapping("/employee/register")
	public String registerEmployee(@Valid User user, BindingResult result, Model model) {
		
		if(result.hasErrors())
		  {
			return "add-employee";
		  }
		
		if(userRepo.findByEmail(user.getEmail()) != null) {
			String exist_email = "Exist email !";
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
			return "home";
		}
	}
}
