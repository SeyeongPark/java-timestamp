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
@Table(name = "Manager")
public class Manager {
 @Id
 @Column(name = "manager_id")
 private int managerId;
 @Column(name = "workplace_id")
 private int workplaceId;
 @NotBlank(message = "Last Name is mandatory")
 @Column(name = "name")
 private String name;
 @Column(name = "email")
 @Pattern(regexp = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
    +
    "[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
    +
    "(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
    message = "Invalid.email")
 private String email;
 @Column(name = "password")
 private String password;
 
 public Manager() {

 }
 
// public Manager(int managerId, int workplaceId, String name, String email, String password ) {
//	this.managerId = managerId;
//	this.workplaceId = workplaceId;
//	this.name = name;
//	this.email = email;
//	this.password = password;
// }

public int getManagerId() {
	return managerId;
}


public void setManagerId(int managerId) {
	this.managerId = managerId;
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

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

}
