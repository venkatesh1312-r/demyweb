package com.demy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demy.Entites.EmployeeEntity;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> 
{

	 @Query(value = "SELECT * FROM employees WHERE email =?1 AND password = ?2", nativeQuery = true)
	    EmployeeEntity findByEmailAndPassword(String email,String password);
	
	 @Query(value = "SELECT * FROM employees WHERE email = ?1", nativeQuery = true)
     EmployeeEntity findByEmail(String email);
	 
	 @Query(value = "SELECT * FROM employees WHERE LOWER(role) = LOWER(?1)", nativeQuery = true)
     EmployeeEntity findByRole(String role);
	 
	 @Transactional
	    @Modifying
	    @Query(value = "UPDATE employees SET password = ?2 WHERE email = ?1", nativeQuery = true)
	    void updatePasswordByEmail(String email, String newPassword);// Proceed with the update query
	    
	 @Transactional
	 @Modifying
	 @Query(value="update employees set name=?1, password=?2,role=?3 where id=?4")
	 void updateEmployee(String name,String email,String password,Long long1);
	
}
