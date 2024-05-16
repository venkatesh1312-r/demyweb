package com.demy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demy.Entites.Leaves;

@Repository
public interface LeaveRepository extends JpaRepository<Leaves, Long> 
{

	@Query(value = "SELECT * FROM leaves WHERE id IN (:id)", nativeQuery = true)
    Leaves findByIdLeaveId(int id);
	
	@Query(value = "SELECT * FROM leaves WHERE employee_email IN (:email)", nativeQuery = true)
    List<Leaves> findByIds(String email);
	
	@Query(value = "SELECT COUNT(*) FROM leaves WHERE employee_email IN (:email)", nativeQuery = true)
    Long getAppliedLeaveCount(String email);
	
	@Query(value = "SELECT COUNT(*) FROM leaves WHERE employee_email IN (:email) AND Status="+2, nativeQuery = true)
    Long getApprovedLeaveCount(String email);
	
	 @Transactional
	    @Modifying
	    @Query(value = "UPDATE leaves SET Status = 1 WHERE id = :leaveId", nativeQuery = true)
	    void updateLeaveStatusToOne(@Param("leaveId") int leaveId);
	
	 @Transactional
	    @Modifying
	    @Query(value = "UPDATE leaves SET Status = 2 WHERE id = :leaveId", nativeQuery = true)
	    void updateLeaveStatusToTwo(@Param("leaveId") int leaveId);
	 
	 @Transactional
	    @Modifying
	    @Query(value = "UPDATE leaves SET Status = 3 WHERE id = :leaveId", nativeQuery = true)
	    void updateLeaveStatusToThree(@Param("leaveId") int leaveId);

}
