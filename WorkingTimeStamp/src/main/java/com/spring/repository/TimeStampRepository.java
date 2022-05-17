package com.spring.repository;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.spring.entity.TimeStamp;
import com.spring.entity.User;

@Repository
public interface TimeStampRepository extends JpaRepository<TimeStamp, Integer>{
	 
	// USE 'Native' way for using LIMIT query order
	// NEED use column name as DB column
	@Query(nativeQuery = true, value = "SELECT * FROM TimeStamp t WHERE (t.userName = :username) ORDER BY t.end_time ASC LIMIT 1")
	TimeStamp findActiveTSByEmail(@Param("username") String username);
	
	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findCurrentUserByEmail(String email);

	public List<TimeStamp> findByWorkplaceIdOrderByStartTimeDesc(int workplaceId);
}
 