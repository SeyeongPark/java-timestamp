package com.spring.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.spring.entity.TimeStamp;
import com.spring.entity.User;
import com.spring.repository.*;
@Controller
public class TimeStampController {
	
	@Autowired
	private TimeStampRepository timeRepo;
	
	@GetMapping("/add/timestamp")
	public String getAddTimeStamp() {
		return "add-timestamp";
	}
	
	@PostMapping("/add/timestamp")
	public String addTimeStamp(@Valid TimeStamp timestamp, @CurrentSecurityContext(expression="authentication?.name")
    String username , Model model) {
		TimeStamp user = timeRepo.findActiveTSByEmail(username);

		User currentUser = timeRepo.findCurrentUserByEmail(username);
		// current date time
		LocalDateTime currentTime = LocalDateTime.now(ZoneOffset.UTC);

		// If the user want to "start" TimeStamp
		if(user == null || user.getEndTime() != null){
			timestamp.setStartTime(currentTime);	
			timestamp.setWorkplaceId(currentUser.getWorkplaceId());	
		}
		
		// If the user want to "end" TimeStamp
		else if(user != null && user.getEndTime() == null) {
			
			LocalDateTime startTime = user.getStartTime();
			int hours = (int) ChronoUnit.MINUTES.between(startTime, currentTime); 
	
			timestamp.setTimeStampId(user.getTimeStampId());
			timestamp.setWorkplaceId(user.getWorkplaceId());
			timestamp.setStartTime(user.getStartTime());
			timestamp.setEndTime(currentTime);
			timestamp.setDayHours(hours);
			int totalHour = (int)currentUser.getTotalHour() + hours;
			currentUser.setTotalHour(totalHour);
			
			double currentWage = currentUser.getTotalHour() * currentUser.getSalary();
			currentUser.setCurrentWage(currentWage);
		}
		
		String name = currentUser.getName();
		int userWorkplaceId = currentUser.getWorkplaceId();

		model.addAttribute("name", name);
		model.addAttribute("localDateTime", LocalDateTime.now());
		model.addAttribute("workplaceId", userWorkplaceId);
		model.addAttribute("position", currentUser.getPosition());
		model.addAttribute("jobTitle", currentUser.getJobTitle());
		model.addAttribute("email", currentUser.getEmail());

		timestamp.setUserName(username);
		timeRepo.save(timestamp);
		model.addAttribute("timestamps", timeRepo.findByWorkplaceIdOrderByStartTimeDesc(userWorkplaceId));
		return "home";
	}
	
	@GetMapping("/timestamps")
	public String getViewTimeStamps(Model model) {
		model.addAttribute("timestamps", timeRepo.findAll(Sort.by(Sort.Direction.ASC, "startTime")));
		return "timestamps";
	}
	
	@GetMapping("/timestamp/delete/{id}")
	public String deleteTimeStamp(@PathVariable("id") int id, Model model) {
		TimeStamp timestamp = timeRepo.findById(id)
				.orElseThrow(()-> new IllegalArgumentException("Invalid timestamp ID, " + id));
		
		timeRepo.delete(timestamp);
		model.addAttribute("timestamps", timeRepo.findAll());

		return "timestamps";
	}
}


