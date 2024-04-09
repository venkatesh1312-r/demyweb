package com.demy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demy.Entites.EmployeeEntity;
import com.demy.Entites.GetTouch;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> 
{

	 @Query(value = "SELECT * FROM Employees WHERE email = :email AND password = :password", nativeQuery = true)
	    EmployeeEntity findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
	 @Query(value = "SELECT * FROM Employees WHERE email = ?1", nativeQuery = true)
     EmployeeEntity findByEmail(String email);
	 
	 @Transactional
	    @Modifying
	    @Query(value = "UPDATE Employees SET password = ?2 WHERE email = ?1", nativeQuery = true)
	    void updatePasswordByEmail(String email, String newPassword);// Proceed with the update query
	    
	
}
