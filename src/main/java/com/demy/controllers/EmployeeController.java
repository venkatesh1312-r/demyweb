package com.demy.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demy.Entites.EmployeeEntity;
import com.demy.services.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController 
{
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	

	
	public EmployeeService getEmployeeService() {
		return employeeService;
	}

	public void setEmployeeService(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/logout")
	public String getLogOut()
	{
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/staff")
	public String getStaffDetails(Model model)
	{
		List<EmployeeEntity> employees=employeeService.findAll();
		System.out.println(employees);
		model.addAttribute("employees", employees);
		return "staff";
	}
	
	@GetMapping("/addEmployee")
	public String addEmployees()
	{
		
		return "addemployee";
	}

	
	@PostMapping("/addEmployee")
	public String addEmployee(@ModelAttribute("emp") EmployeeEntity emp,Model model)
	{
//		System.out.println(emp.toString());
		
		EmployeeEntity empByEmail=employeeService.findEmail(emp.getEmail());
		
		if(empByEmail!=null)
		{
			model.addAttribute("Errormsg", "Employee added Prevously");
			
			return "addemployee";
	
		}
		
		EmployeeEntity addedEmployee=employeeService.addEmployee(emp);
		
		if(addedEmployee!=null)
		{
           HttpStatus codes=employeeService.sendAddEmail(emp.getEmail(),emp.getName());
    		
    		if(codes.value()==200)
			 {
    			model.addAttribute("msg", "Employee added Successfully");

    			
			 }
    		
		    	return "addemployee";
			
		}
		return "staff";
	}
	
	@GetMapping("/delete")
	public String deleteEmployee(@RequestParam("id") Long id,Model model)
	{
		employeeService.delete(id);
		model.addAttribute("msg", "Employee Deleted Successfully");
        return "redirect:/staff";
	}
	
	@GetMapping("/edit")
	public String editEmployee(@RequestParam("id") Long id,Model model)
	{
		Optional<EmployeeEntity> optionalEmp = employeeService.findById(id);
		
		EmployeeEntity emp = optionalEmp.orElse(new EmployeeEntity()); // Get the wrapped object or create a new one if it's empty

		
		System.out.println(emp.toString());
		
		model.addAttribute("emp",emp);
		
        return "editemployee";
	}
	
	@PostMapping("/edit")
	public String editEmployeePost(@ModelAttribute("emp") EmployeeEntity emp,Model model)
	{
//		System.out.println(emp.toString());
		
		employeeService.update(emp);
		
		return null;
	}


	
}
