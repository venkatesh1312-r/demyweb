package com.demy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demy.Entites.Leaves;

@Repository
public interface LeaveRepository extends JpaRepository<Leaves, Long> 
{

	
	@Query(value = "SELECT * FROM leaves WHERE employee_email IN (:email)", nativeQuery = true)
    List<Leaves> findByIds(String email);
}
