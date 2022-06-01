package com.spring.controller;

import javax.swing.ImageIcon;
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
import com.spring.repository.TimeStampRepository;
import com.spring.repository.UserRepository;


@Controller
public class ManagerController {
	
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private TimeStampRepository timeRepo;
		
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
		// check exist user or not
		if(userRepo.findByEmail(user.getEmail()) != null) {
			String exist_email = "An account already exist with this email.";
			model.addAttribute("exist_email", exist_email);
			return "add-manager";
		}
		
		// encode entered password
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		user.setPosition("manager");
		user.setSalary(0);
		// save data into Manager DB table
		userRepo.save(user);
		return "login";
	}
	
	@GetMapping("/manager/employees")
	public String checkEmployees(Model model, @CurrentSecurityContext(expression="authentication?.name") String username) {
		
		User currentUser = userRepo.findByEmail(username);
		int workplaceId = currentUser.getWorkplaceId();
		int userWorkplaceId = currentUser.getWorkplaceId();
		
		ImageIcon im = new ImageIcon("image/SP_logo_white.png"); 
		// Employee cannot access this page
		if(currentUser.getPosition().contains("employee")){
			model.addAttribute("timestamps", timeRepo.findByWorkplaceIdOrderByStartTimeDesc(userWorkplaceId));
			return "access-denied";
		}
		else {
			model.addAttribute("employees", userRepo.findByWorkplaceId(workplaceId));
			model.addAttribute("workplaceId", userWorkplaceId);
			return "my-employees";
		}
	}
	
	@GetMapping("/manager/employee/update/{userId}")
	public String getUpdateEmployee(@PathVariable("userId") int id, Model model) {
		User employee = userRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user ID : " + id));
		int userWorkplaceId = employee.getWorkplaceId();
		
		model.addAttribute("employee", employee);
		model.addAttribute("workplaceId", userWorkplaceId);

		return "update-employee";
	}
	
	@PostMapping("/manager/employee/update/{userId}")
	public String updateMyEmployee(@PathVariable("userId") int id, @Valid User employee, 
			BindingResult result, Model model,
			@CurrentSecurityContext(expression="authentication?.name") String username) {

		int workplaceId = employee.getWorkplaceId();
		String position = employee.getPosition();
		
		if (result.hasErrors()) {
			 employee.setUserId(id);			 
		 }
		 employee.setPosition(position);
		 employee.setWorkplaceId(workplaceId);
		 
		 userRepo.save(employee);
		 model.addAttribute("employees", userRepo.findByWorkplaceId(workplaceId));
		 model.addAttribute("workplaceId", workplaceId);

		return "my-employees";
	}
}
