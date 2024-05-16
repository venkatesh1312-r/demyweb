package com.demy.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demy.Entites.EmployeeEntity;
import com.demy.Entites.Leaves;
import com.demy.repositories.EmployeeRepository;
import com.demy.repositories.LeaveRepository;

@Service
public class leaveServiceImpl implements LeavesService {


	@Autowired
	private LeaveRepository leaverepo;

	@Autowired
	private JavaMailSender javaMailSender;
	

	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public EmployeeRepository getEmployeeRepository() {
		return employeeRepository;
	}


	public void setEmployeeRepository(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}


	public LeaveRepository getLeaverepo() {
		return leaverepo;
	}


	public void setLeaverepo(LeaveRepository leaverepo) {
		this.leaverepo = leaverepo;
	}


	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}



	@Override
	public int applyLeaveFormSubmission(Leaves leaveForm) 
	{
		 Leaves appliedleave=leaverepo.save(leaveForm);
		 
		 if ("manager".equals(appliedleave.getEmployeeRole().toLowerCase()) || "hr".equals(appliedleave.getEmployeeRole().toLowerCase())) 
		 {
		    appliedleave.setStatus(1);
		    
		    if("manager".equals(appliedleave.getEmployeeRole().toLowerCase()))
		    {
		    	appliedleave.setStatus(2);
		    }
		    leaverepo.save(appliedleave);
		    
			HttpStatus codes=LeaveApprovalConfirmationMail(leaveForm); 
			
			if (codes.value() == 200) 
			{
				return 2;
			}
			else
			{
				return 3;
			}

		 }
		 Date startDate = leaveForm.getStartDate();
		 Date endDate = leaveForm.getEndDate();
		    
		    
		// Format the startDate and endDate into yyyy-MM-dd format
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    String formattedStartDate = dateFormat.format(startDate);
		    String formattedEndDate = dateFormat.format(endDate);
		    
		   
		 
		    if(appliedleave!=null)
		    {
		    	// Call applyLeaveConfirmationMail
		        HttpStatus status = applyLeaveConfirmationMail(appliedleave);
		        
		        applyLeaveMailtoAuthorties(appliedleave,formattedStartDate,formattedEndDate);


				if (status.value() == 200) 
				{				
		    	return 1;
				}
		    }
		    
		return 0;
	}


	@Override
	public HttpStatus applyLeaveConfirmationMail(Leaves leaveForm) 
	{
		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    
		    message.setSubject("Confirmation: Leave Application Submitted Successfully at DSS");

		    message.setText("Dear " + leaveForm.getEmployeeName() + ",\r\n"
		            + "\r\n"
		            + "We hope this message finds you well.\r\n"
		            + "\r\n"
		            + "We are writing to confirm that your leave application has been submitted successfully.\r\n"
		            + "\r\n"
		            + "Your request will now be reviewed by the appropriate authorities. Once approved by your manager and HR, your leave status will be updated accordingly.\r\n"
		            + "\r\n"
		            + "We will notify you of any updates regarding the status of your leave application.\r\n"
		            + "\r\n"
		            + "In the meantime, if you have any questions or concerns, feel free to reply to this email.\r\n"
		            + "\r\n"
		            + "Thank you for choosing Demy Software Solutions.\r\n"
		            + "\r\n"
		            + "Best regards,\r\n"
		            + "\r\n"
		            + "DSS Leave Management Team");

		    message.setTo(leaveForm.getEmployeeEmail());

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) {
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}	
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
	}


	@Override
	public HttpStatus applyLeaveMailtoAuthorties(Leaves leaveForm,String startDate,String endDate) 
	{
	    try {
	        SimpleMailMessage message = new SimpleMailMessage();

	        message.setSubject("Leave Application Alert: New Leave Application at DSS");
             
	    
	        EmployeeEntity e1=employeeRepository.findByRole("manager");
	        
	        EmployeeEntity e2=employeeRepository.findByRole("hr");

	        
	        String managerEmail =e1.getEmail(); 
	        String hrEmail = e2.getEmail(); 

	        // Message for manager
	        String managerMessage = "Dear Manager,\r\n"
	                + "\r\n"
	                + "A new leave application has been submitted by " + leaveForm.getEmployeeName() + ".\r\n"
	                + "\r\n"
	                + "Leave Details:\r\n"
	                + "Start Date: " + startDate + "\r\n"
	                + "End Date: " + endDate + "\r\n"
	                + "Reason: " + leaveForm.getReason() + "\r\n"
	                + "\r\n"
	                + "Please review and approve the leave request as soon as possible.\r\n"
	                + "\r\n"
	                + "Thank you.\r\n"
	                + "\r\n"
	                + "Best regards,\r\n"
	                + "\r\n"
	                + "DSS Leave Management Team";

	        // Message for HR
	        String hrMessage = "Dear HR,\r\n"
	                + "\r\n"
	                + "A new leave application has been submitted by " + leaveForm.getEmployeeName() + ".\r\n"
	                + "\r\n"
	                + "Leave Details:\r\n"
	                + "Start Date: " + startDate + "\r\n"
	                + "End Date: " + endDate + "\r\n"
	                + "Reason: " + leaveForm.getReason() + "\r\n"
	                + "\r\n"
	                + "Please review and approve the leave request as soon as possible.\r\n"
	                + "\r\n"
	                + "Thank you.\r\n"
	                + "\r\n"
	                + "Best regards,\r\n"
	                + "\r\n"
	                + "DSS Leave Management Team";

	        // Send email to manager
	        sendEmail(managerEmail, "Leave Application Alert: New Leave Application", managerMessage);

	        // Send email to HR
	        sendEmail(hrEmail, "Leave Application Alert: New Leave Application", hrMessage);

	        return HttpStatus.OK; // Email sent successfully
	    } catch (Exception e) {
	        return HttpStatus.INTERNAL_SERVER_ERROR; // Failed to send email
	    }
	}
	
	private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }


	@Override
	public HttpStatus LeaveApprovalConfirmationMail(Leaves leaveForm) {

		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    
		    message.setSubject("Confirmation: Leave Application Approved Successfully at DSS");

		    message.setText("Dear " + leaveForm.getEmployeeName() + ",\r\n"
		            + "\r\n"
		            + "We hope this message finds you well.\r\n"
		            + "\r\n"
		            + "We are pleased to inform you that your leave application has been approved successfully.\r\n"
		            + "\r\n"
		            + "Your leave status has been updated accordingly. You can now proceed with your planned leave.\r\n"
		            + "\r\n"
		            + "If you have any questions or need further assistance, please feel free to contact us.\r\n"
		            + "\r\n"
		            + "Thank you for choosing Demy Software Solutions.\r\n"
		            + "\r\n"
		            + "Best regards,\r\n"
		            + "\r\n"
		            + "DSS Leave Management Team");

		    message.setTo(leaveForm.getEmployeeEmail());

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) {
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}	
	}


	@Override
	public List<Leaves> leavesStatus(String email) 
	{
		List<Leaves> leaves=leaverepo.findByIds(email);
		
		return leaves;
	}


	@Override
	public Long getApprovedLeaveCount(String email) {
		// TODO Auto-generated method stub
		return leaverepo.getApprovedLeaveCount(email);
	}


	@Override
	public Long getAppliedLeaveCount(String email) 
	{
		// TODO Auto-generated method stub
		return leaverepo.getAppliedLeaveCount(email);
	}


	@Override
	public List<Leaves> leavesStatus() {
		// TODO Auto-generated method stub
		return leaverepo.findAll();
	}


	@Override
	public int approve(int id, int role,Leaves leaveEntity) {
		// TODO Auto-generated method stub
		
		if(role==1)
		{
			leaverepo.updateLeaveStatusToOne(id);

		}
		else
		{
			leaverepo.updateLeaveStatusToTwo(id);
			LeaveApprovalConfirmationMail(leaveEntity);
		}
		
		
		return 0;
	}


	@Override
	public Leaves findByIds(int id)
	{
		return leaverepo.findByIdLeaveId(id);
	}


	@Override
	public int disApprove(int id) 
	{
		// TODO Auto-generated method stub
		
		leaverepo.updateLeaveStatusToThree(id);
		return 1;
	}


	
}
