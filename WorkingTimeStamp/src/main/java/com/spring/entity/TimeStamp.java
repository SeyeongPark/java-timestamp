package com.spring.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "timestamp")
public class TimeStamp {
	@Id
	@Column(name = "id")
	private int timeStampId;
	@Column(name = "workplace_id")
	private int workplaceId;
	@Column(name = "username")
	private String userName;
	@Column(name = "start_time")
	private LocalDateTime startTime;
	@Column(name = "end_time")
	private LocalDateTime endTime;
	@Column(name = "day_hours")
	private long dayHours;

	public TimeStamp() {
		
	}

	public int getTimeStampId() {
		return timeStampId;
	}

	public void setTimeStampId(int timeStampId) {
		this.timeStampId = timeStampId;
	}

	public int getWorkplaceId() {
		return workplaceId;
	}

	public void setWorkplaceId(int workplaceId) {
		this.workplaceId = workplaceId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public LocalDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDateTime startTime) {
		this.startTime = startTime;
	}

	public LocalDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDateTime endTime) {
		this.endTime = endTime;
	}

	public long getDayHours() {
		return dayHours;
	}

	public void setDayHours(long dayHour) {
		dayHours = dayHour;
	}
}
