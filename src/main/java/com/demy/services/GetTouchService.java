package com.demy.services;

import org.springframework.http.HttpStatus;

import com.demy.Entites.GetTouch;

public interface GetTouchService 
{
	public GetTouch addForm(GetTouch data);
	
	  public GetTouch findByEmail(String email);

	  public HttpStatus sendEmail(String email,String user);
	  	  
	    public Long getTouchCount();


}
