package com.demy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demy.Entites.GetTouch;

public interface GetTouchRepository extends JpaRepository<GetTouch,Integer> 
{
	@Query(value = "SELECT * FROM get_touch WHERE email = ?1", nativeQuery = true)
     GetTouch findByEmail(String email);
}
