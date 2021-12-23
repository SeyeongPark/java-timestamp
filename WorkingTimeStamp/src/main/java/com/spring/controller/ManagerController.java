package com.spring.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.Manager;
import com.spring.repository.ManagerRepository;


@Controller
public class ManagerController {
	
	@Autowired
	private ManagerRepository manaRepo;
	
	//To get the add-card.html page
	@GetMapping("/")
	public String homeCard() {
		return "home";
	}
	
	@GetMapping("/manager/register")
	public String getRegisterManager(Manager manager) {
		return "add-manager";
	}
	
	@PostMapping("/manager/register")
	public String registerManager(@Valid Manager manager,  BindingResult result, Model model) {
		 
		if(result.hasErrors())
		  {
			return "add-manager";
		  }
		  
		manaRepo.save(manager);
		return "home";
	}
}
