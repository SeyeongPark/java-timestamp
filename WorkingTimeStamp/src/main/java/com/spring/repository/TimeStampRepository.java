package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.entity.TimeStamp;

@Repository
public interface TimeStampRepository extends JpaRepository<TimeStamp, Integer>{
	
	// Want to find the recent value of 'status' is "Start" or not
	// If yes, I can handle next value of 'staus' will be "End" 
	// with storing 'endTime'
	public TimeStamp isStatusStart(String email);
}
