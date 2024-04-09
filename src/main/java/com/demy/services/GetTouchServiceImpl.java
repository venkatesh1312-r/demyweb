package com.demy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.demy.Entites.GetTouch;
import com.demy.repositories.GetTouchRepository;

@Service
public class GetTouchServiceImpl implements GetTouchService {

	
	@Autowired
	private GetTouchRepository getTouchRepository;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public GetTouchRepository getGetTouchRepository() {
		return getTouchRepository;
	}
	public void setGetTouchRepository(GetTouchRepository getTouchRepository) {
		this.getTouchRepository = getTouchRepository;
	}
	@Override
	public GetTouch addForm(GetTouch data) 
	{
		
		// TODO Auto-generated method stub
		return 	getTouchRepository.save(data);

	}
	@Override
	public HttpStatus sendEmail(String email, String user) 
	{
		try {
		    SimpleMailMessage message = new SimpleMailMessage();
		    message.setSubject("Confirmation: Successful Details Upload at DSS Careers");
		    message.setText("Dear " + user + ",\r\n"
		            + "\r\n"
		            + "We hope this message finds you well.\r\n"
		            + "\r\n"
		            + "We are writing to confirm that we have received your details successfully through our DSS Get in Touch platform. Thank you for reaching out and expressing interest in connecting with our team at Demy Software Solutions.\r\n"
		            + "\r\n"
		            + "Your inquiry is important to us, and our team will now review it promptly. We will reach out to you shortly to address your query or discuss further details as needed.\r\n"
		            + "\r\n"
		            + "In the meantime, if you have any additional questions or would like to provide further information, feel free to reply to this email or contact us directly through the DSS Get in Touch platform.\r\n"
		            + "\r\n"
		            + "Thank you once again for considering Demy Software Solutions. We look forward to the opportunity to assist you.\r\n"
		            + "\r\n"
		            + "Best regards,\r\n"
		            + "\r\n"
		            + "DSS Customer Support Team");

		    message.setTo(email);

		    javaMailSender.send(message);

		    return HttpStatus.OK; // Email sent successfully
		} catch (Exception e) {
		    e.printStackTrace();
		    return HttpStatus.INTERNAL_SERVER_ERROR; // Internal Server Error		}
		// TODO Auto-generated method stub
		}	
		// TODO Auto-generated method stub
	}

	@Override
	public GetTouch findByEmail(String email) {
		
		
		return getTouchRepository.findByEmail(email);
	}

	@Override
	public Long getTouchCount() {
		// TODO Auto-generated method stub
		return getTouchRepository.count();
	}

	
	


}
