package com.demy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demy.Entites.Career;
import com.demy.repositories.CareerRepository;

@Service
public class CareerImpl implements CareerService{

	
	@Autowired
	private CareerRepository careerRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	
	
	
	public CareerRepository getCareerRepository() {
		return careerRepository;
	}

	public void setCareerRepository(CareerRepository careerRepository) {
		this.careerRepository = careerRepository;
	}

	@Override
	public Career addCareerForm(Career career) 
	{
		
      
		return careerRepository.save(career);
	}

	@Override
	public HttpStatus sendEmail(String email,String user) 
	{
		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    message.setSubject("Confirmation: Successful Details Upload at DSS Careers");
		    message.setText("Dear "+user+","+"\r\n"
		    		+ "\r\n"
		    		+ "We hope this message finds you well.\r\n"
		    		+ "\r\n"
		    		+ "We are writing to confirm that we have received your details successfully through our DSS Careers platform. Thank you for taking the time to submit your information and express interest in joining our team at Demy Software Solutions.\r\n"
		    		+ "\r\n"
		    		+ "Your application is important to us, and our HR team will now proceed with the review process. We will carefully assess your qualifications and experience against our criteria. If your profile matches our current requirements, we will reach out to you for further discussion or to schedule an interview.\r\n"
		    		+ "\r\n"
		    		+ "Thank you once again for considering a career opportunity with Demy Software Solutions. We appreciate your interest in being part of our innovative and dynamic team.\r\n"
		    		+ "\r\n"
		    		+ "Best regards,\r\n"
		    		+ "\r\n"
		    		+ "HR Team Mahalaskhmi\r\n"
		    		+ "Demy Software Solutions");
		    message.setTo(email);

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) {
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}	
	}

	@Override
	public Long careerCount() {
		// TODO Auto-generated method stub
		return careerRepository.count();
	}
}
