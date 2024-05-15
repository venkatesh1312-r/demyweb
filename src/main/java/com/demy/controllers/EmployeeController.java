package com.demy.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.demy.Entites.Career;
import com.demy.Entites.EmployeeEntity;
import com.demy.repositories.CareerRepository;
import com.demy.services.CareerImpl;
import com.demy.services.CareerService;
import com.demy.services.EmployeeService;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeController 
{
	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private CareerRepository careerRepository;

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
	public String getStaffDetails(Model model, HttpSession session) {
	    EmployeeEntity loggedInEmployee = (EmployeeEntity) session.getAttribute("loggedInEmployee");

	    if (loggedInEmployee != null && "manager".equalsIgnoreCase(loggedInEmployee.getRole().toLowerCase())) 
	    {
	        List<EmployeeEntity> employees = employeeService.findAll();
	        model.addAttribute("employees", employees);
	    } else {
	        if (loggedInEmployee != null) {
		    	String msg1="display:none";

	            EmployeeEntity employee = employeeService.findEmail(loggedInEmployee.getEmail());
	            model.addAttribute("employee", employee);
		        model.addAttribute("msg1",msg1);

	        }
	    }

	    return "staff";
	}

	
	@GetMapping("/addEmployee")
	public String addEmployees()
	{
		
		return "addemployee";
	}

	
	@PostMapping("/addEmployee")
	public String addEmployee(@RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("role") String role,
            @RequestParam("offerLetter") MultipartFile file,
            Model model) {
		try {
		    // Assuming EmployeeEntity is a model/entity class representing an employee
		    EmployeeEntity emp = new EmployeeEntity();
		    emp.setName(name);
		    emp.setEmail(email);
		    emp.setPassword(password);
		    emp.setRole(role);

		    emp.setOfferLetter(file.getOriginalFilename());
		    
		    File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
			myFile.createNewFile();
			FileOutputStream fos = new FileOutputStream(myFile);
			fos.write(file.getBytes());
			fos.close();			
			
			EmployeeEntity empByEmail=employeeService.findEmail(emp.getEmail());
			
			if(empByEmail!=null)
			{
				model.addAttribute("Errormsg", "Employee added Prevously");
				
				return "addemployee";
		
			}
			
			EmployeeEntity addedEmployee=employeeService.addEmployee(emp);
			
			if(addedEmployee!=null)
			{
	           HttpStatus codes=employeeService.sendAddEmail(emp.getEmail(),emp.getName(),emp.getOfferLetter());
	    		
	    		if(codes.value()==200)
				 {
	    			model.addAttribute("msg", "Employee added Successfully");

				 }
	    		
			    	return "addemployee";
				
			}
				
		}
		catch(Exception e)
		{
			
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
		
		employeeService.updateEmployee(emp);
		
		model.addAttribute("msg", "Updated Successfully");
		
		return "editemployee";
	}

	
	@GetMapping("/viewCareers")
	public String viewCareerRecords(Model model)
	{
		List<Career> careers=careerRepository.findAllHighestIdByEmail();
		
		System.out.println(careers);
		
		model.addAttribute("careers", careers);
		
		return "viewCareers";
	}
	
	@GetMapping("/viewCareerDetails")
	public String viewCareerPersonRecord(@RequestParam("id") Long id, Model model) 
	{
	    // Assuming you have a method in your repository to find a career by its ID
	    Career career = careerRepository.findByCareerId(id);
	    
	    // Check if the career exists
	    if(career != null) 
	    {
	        // If the career exists, you might want to do something with it
	        System.out.println(career);
	        model.addAttribute("career", career);
	        
	        return "viewCareerDetails";
	    } 
	    return "viewCareers"; // Assuming you have a view named viewCareers
	}


}