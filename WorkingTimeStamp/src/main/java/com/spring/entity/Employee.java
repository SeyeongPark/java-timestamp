package com.spring.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;

@Entity
@Table(name = "Employee")
public class Employee {

 @Id
 private int emp_id;
 private int manager_id;
 private int workplace_id;
 private String name;
 private double salary;
 private Date start_time;
 private Date end_time;
 private String email;
 private double password;

 public Employee() {
	 
 }

public int getEmp_id() {
	return emp_id;
}

public void setEmp_id(int emp_id) {
	this.emp_id = emp_id;
}

public int getManager_id() {
	return manager_id;
}

public void setManager_id(int manager_id) {
	this.manager_id = manager_id;
}

public int getWorkplace_id() {
	return workplace_id;
}

public void setWorkplace_id(int workplace_id) {
	this.workplace_id = workplace_id;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public double getSalary() {
	return salary;
}

public void setSalary(double salary) {
	this.salary = salary;
}

public Date getStart_time() {
	return start_time;
}

public void setStart_time(Date start_time) {
	this.start_time = start_time;
}

public Date getEnd_time() {
	return end_time;
}

public void setEnd_time(Date end_time) {
	this.end_time = end_time;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public double getPassword() {
	return password;
}

public void setPassword(double password) {
	this.password = password;
}
}
