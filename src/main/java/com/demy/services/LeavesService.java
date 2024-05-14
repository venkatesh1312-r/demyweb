package com.demy.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.demy.Entites.Leaves;

public interface LeavesService 
{
	public int applyLeaveFormSubmission(Leaves leaveForm);
	
	public HttpStatus applyLeaveConfirmationMail(Leaves leaveForm);
	
	public HttpStatus applyLeaveMailtoAuthorties(Leaves leaveForm,String startDate,String endDate);
	
	public HttpStatus LeaveApprovalConfirmationMail(Leaves leaveForm);
	
	public List<Leaves> leavesStatus(String email);





}
