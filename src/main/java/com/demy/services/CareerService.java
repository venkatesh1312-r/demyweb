package com.demy.services;

import org.springframework.http.HttpStatus;

import com.demy.Entites.Career;

public interface CareerService
{
	
	  public Career addCareerForm(Career career);
	  

	    public Long careerCount();

	  
	  public HttpStatus sendEmail(String email,String user);
	  
	


}
