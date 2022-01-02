package com.spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.spring.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	
//	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByEmail(String email);
	
	//@Query("SELECT u FROM User u WHERE :position ")
	List<User> findByWorkplaceId(int workplaceId);
	
	//@Query("SELECT SUM()")
	//int calculateTotal(@Param("username") String email);
}
