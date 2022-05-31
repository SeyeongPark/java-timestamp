package com.spring.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

@Entity
@Table(name = "User")
public class User {
 @Id
 @Column(name = "id")
 private int userId;
 @Column(name="position")
 private String position;
 @Column(name="job_title")
 private String jobTitle;
 @Column(name = "workplace_id")
 private int workplaceId;
 @NotBlank(message = "Name is mandatory")
 @Column(name = "full_name")
 private String name;
 @Column(name = "email", unique=true)
 @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
    +
    "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
    +
    "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
    message = "Invalid.email")
 private String email;
 @Column(name = "password")
 private String phone;
 @Column(name = "phone")
 private String password;
 @Column(name = "total_hour")
 private long totalHour;
 
 @Column(name = "salary")
 private double salary;
 @Column(name = "current_wage")
 private double currentWage;


 public User() {

 }

public int getUserId() {
	return userId;
}

public void setUserId(int userId) {
	this.userId = userId;
}

public String getPosition() {
	return position;
}

public void setPosition(String position) {
	this.position = position;
}

public String getJobTitle() {
	return jobTitle;
}

public void setJobTitle(String jobTitle) {
	this.jobTitle = jobTitle;
}

public int getWorkplaceId() {
	return workplaceId;
}

public void setWorkplaceId(int workplaceId) {
	this.workplaceId = workplaceId;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public long getTotalHour() {
	return totalHour;
}

public void setTotalHour(long totalHour) {
	this.totalHour = totalHour;
}

public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}

public double getCurrentWage() {
	return currentWage;
}

public void setCurrentWage(double currentWage) {
	this.currentWage = currentWage;
}

}