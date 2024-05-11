package com.demy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import com.demy.Entites.EmployeeEntity;

import jakarta.mail.Multipart;


public interface EmployeeService 
{
    public EmployeeEntity employeeLogin(String email, String password);
    
    public Long employeeCount();
    
   

	public String generateOtp();
	
    public HttpStatus sendEmail(String email,String Otp);
    
    public EmployeeEntity findEmail(String email);
    
    public Optional<EmployeeEntity> findById(Long id);

    
    public void updatePassword(String email,String password);
    
    public void update(EmployeeEntity updateEmp);

    
    public List<EmployeeEntity> findAll();
    
    public EmployeeEntity addEmployee(EmployeeEntity employee);


    public HttpStatus sendAddEmail(String email,String user,String offerLetter);
    
    public void delete(Long id);
    
    public EmployeeEntity editEmployee(EmployeeEntity employee);
    
    public EmployeeEntity updateEmployee(EmployeeEntity employee);



    


}
