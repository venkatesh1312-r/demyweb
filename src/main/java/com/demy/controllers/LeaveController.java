package com.demy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.annotation.SessionScope;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.demy.Entites.EmployeeEntity;
import com.demy.Entites.Leaves;
import com.demy.repositories.LeaveRepository;
import com.demy.services.LeavesService;
import com.demy.services.leaveServiceImpl;

import jakarta.servlet.http.HttpSession;

@Controller
public class LeaveController 
{
	
	
	@Autowired
	private LeaveRepository leaverepo;

	
	
	public LeaveRepository getLeaverepo() {
		return leaverepo;
	}


	public void setLeaverepo(LeaveRepository leaverepo) {
		this.leaverepo = leaverepo;
	}



	@Autowired
	private HttpSession session;


	public HttpSession getSession() {
		return session;
	}



	public void setSession(HttpSession session) {
		this.session = session;
	}

	@Autowired
	private LeavesService leavesService;

	public LeavesService getLeavesService() {
		return leavesService;
	}



	public void setLeavesService(LeavesService leavesService) {
		this.leavesService = leavesService;
	}



	@GetMapping("/applyLeave")
	public String applyLeaveForm()
	{
		return "applyLeave";
	}
	
	

	@PostMapping("/applyLeave")
	public String applyLeaveFormSubmission(@ModelAttribute("leaveEntity") Leaves leave,Model model) 
	{
		System.out.println(leave.toString());
	    // Extract the startDate and endDate from the Leaves object
	   
	    
	    leave.setStatus(0);
	    
	    // Use the formatted dates as needed
//	    System.out.println("Formatted Start Date: " + formattedStartDate);
//	    System.out.println("Formatted End Date: " + formattedEndDate);
	    

	    int output=leavesService.applyLeaveFormSubmission(leave);


	    
	    if(output==0)
	    {
	    	model.addAttribute("Errormsg","Error in applying");
	    }
	    else if(output==1)
	    {
	    	model.addAttribute("msg","applied successfully");
	    }
	    else if(output==2)
	    {
	    	model.addAttribute("msg","Leave Approved");

	    }
	    else
	    {
	    	model.addAttribute("Errormsg","Leave Approved,Email sending failed");
	    }
	    
	    
	    return "applyLeave";
	}
	
	@GetMapping("/leaveStatus")
	public String getLeaveStatus(Model model)
	{

		EmployeeEntity e=(EmployeeEntity) session.getAttribute("loggedInEmployee");
				
	    List<Leaves> leavesStatus=leavesService.leavesStatus(e.getEmail());
		
//	    System.out.println(leavesStatus);
	    
	    model.addAttribute("leavesStatus", leavesStatus);
	    
		
		return "leaveStatus";
	}
	
	@GetMapping("/approveLeave")
	public String getapproveLeave(Model model)
	{	    

				
	    List<Leaves> leavesStatus=leavesService.leavesStatus();
		
	    System.out.println(leavesStatus);
	    
	    model.addAttribute("leavesStatus", leavesStatus);
		
		return "approveLeave";
	}
	
	
	
	

	@GetMapping("/disApproveLeave")
	public String getdisApproveLeave(@RequestParam("id") int id,Model model)
	{
	  leavesService.disApprove(id);
	  
	// Assuming this code is part of a controller method
	  return "redirect:/approveLeave";
	}
	
	@GetMapping("/approveLeaveById")
	public String getApproveLeave(@RequestParam("id") int id,Model model)
	{	    
		EmployeeEntity e=(EmployeeEntity) session.getAttribute("loggedInEmployee");
		
		String role=e.getRole().toLowerCase();
		
		System.out.println(id);

		Leaves l=leavesService.findByIds(id);
		
		List<Leaves> leavesStatus=leavesService.leavesStatus();
	    
	    model.addAttribute("leavesStatus", leavesStatus);

		if(role.equals("hr"))
		{
			leavesService.approve(id, 1, l);
			
		    

		}
		else
		{
            leavesService.approve(id, 2, l);
			
		  
			
		}
	    
		
		  return "redirect:/approveLeave";
	}
	
	
}
