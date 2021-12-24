package com.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.spring.entity.Manager;

@Repository
public interface ManagerRepository extends JpaRepository<Manager, Long> {
	
	@Query("SELECT m FROM Manager m WHERE m.email = ?1")
	public Manager findByEmail(String email);
	
}
