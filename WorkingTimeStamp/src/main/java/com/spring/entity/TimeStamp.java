package com.spring.entity;

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
	private Date startTime;
	@Column(name = "end_time")
	private Date endTime;
	@Column(name = "status")
	private String status;	
	@Column(name = "total_hour")
	private int totalHour;
	
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

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int getTotalHour() {
		return totalHour;
	}

	public void setTotalHour(int totalHour) {
		this.totalHour = totalHour;
	}
}
