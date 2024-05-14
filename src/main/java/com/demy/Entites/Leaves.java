package com.demy.Entites;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Leaves 
{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String EmployeeName;
    private String EmployeeEmail;
    private String EmployeeRole;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    
    private Date endDate;
	private String Reason;
    private int Status;
    
    @Override
	public String toString() {
		return "Leaves [id=" + id + ", EmployeeName=" + EmployeeName + ", EmployeeEmail=" + EmployeeEmail
				+ ", EmployeeRole=" + EmployeeRole + ", startDate=" + startDate + ", endDate=" + endDate + ", Reason="
				+ Reason + ", Status=" + Status + "]";
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeName() {
		return EmployeeName;
	}
	public void setEmployeeName(String employeeName) {
		EmployeeName = employeeName;
	}
	public String getEmployeeEmail() {
		return EmployeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		EmployeeEmail = employeeEmail;
	}
	public String getEmployeeRole() {
		return EmployeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		EmployeeRole = employeeRole;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}

}
