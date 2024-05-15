package com.demy.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.demy.Entites.Career;


public interface CareerRepository extends JpaRepository<Career, Long> 
{
	@Query(value = "SELECT * FROM careers c WHERE c.id = (SELECT MAX(c2.id) FROM careers c2 WHERE c.email = c2.email)", nativeQuery = true)
    List<Career> findAllHighestIdByEmail();
	
	 @Query(value = "SELECT * FROM careers WHERE id= ?1", nativeQuery = true)
     Career findByCareerId(Long id);

}


