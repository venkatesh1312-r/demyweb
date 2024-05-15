package com.demy.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrorController 
{
	
	@GetMapping("/error")
	public String getError()
	{
		return "error";
	}
	
	@PostMapping("/error")
	public String postError()
	{
		return "error";
	}

}
