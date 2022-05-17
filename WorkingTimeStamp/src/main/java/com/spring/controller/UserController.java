package com.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.spring.entity.User;
import com.spring.repository.UserRepository;

@Controller
public class UserController {
	
	@Autowired
	private UserRepository userRepo;
	
	@GetMapping("/users")
	public String getUsers(Model model) {
		model.addAttribute("users",userRepo.findAll());
		return "users";
	}
	
	@GetMapping("/user/delete/{id}")
	public String deleteUser(@PathVariable("id") int id, Model model) {
		
		User user = userRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid user ID, " + id));
		
		userRepo.delete(user);
		model.addAttribute("users", userRepo.findAll());
		return "users";
	}	
}
