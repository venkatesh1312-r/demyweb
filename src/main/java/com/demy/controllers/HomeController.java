package com.demy.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.demy.Entites.GetTouch;
import com.demy.services.CareerService;
import com.demy.services.EmployeeService;
import com.demy.services.GetTouchService;
import com.demy.services.GetTouchServiceImpl;
import com.demy.services.LeavesService;

import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
public class HomeController {

	@Value("${file.upload-dir}")
	String FILE_DIRECTORY;

	@Autowired
	private HttpSession session;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CareerService careerService;

	@Autowired
	private GetTouchService getTouchService;

	
	@Autowired
	private LeavesService leavesService;

	public LeavesService getLeavesService() {
		return leavesService;
	}

	public void setLeavesService(LeavesService leavesService) {
		this.leavesService = leavesService;
	}

	public GetTouchService getGetTouchService() {
		return getTouchService;
	}

	public void setGetTouchService(GetTouchService getTouchService) {
		this.getTouchService = getTouchService;
	}

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

	public CareerService getCareerService() {
		return careerService;
	}

	public void setCareerService(CareerService careerService) {
		this.careerService = careerService;
	}

	@GetMapping("/careers")
	public String getCareers() {
		return "careers";
	}

	@GetMapping("/home")
	public String getHome() {
		return "index";
	}

	@GetMapping("/index")
	public String getIndex() {
		return "index";
	}

	@GetMapping("/employeeLogin")
	public String getEmployeeLogin() {
		return "employeelogin";
	}

	@PostMapping("/employeeLogin")
	public String EmployeeLogin(@RequestParam("email") String email, @RequestParam("password") String password,
			Model model) 
	{
		EmployeeEntity loggedInEmployee = employeeService.employeeLogin(email, password);
//System.out.println(loggedInEmployee.toString());
		if (loggedInEmployee != null) {
			session.setAttribute("loggedInEmployee", loggedInEmployee);
			
			Long employeeCount = employeeService.employeeCount();
			Long careerCount = careerService.careerCount();
			Long getTouchCount = getTouchService.getTouchCount();
			Long leavesAppliedCount=leavesService.getAppliedLeaveCount(email);
			Long leavesApprovedCount=leavesService.getApprovedLeaveCount(email);


//			System.out.println(employeeCount);

			model.addAttribute("employeeCount", employeeCount);
			model.addAttribute("careerCount", careerCount);
			model.addAttribute("getTouchCount", getTouchCount);
			model.addAttribute("leavesAppliedCount",leavesAppliedCount);
			model.addAttribute("leavesApprovedCount",leavesApprovedCount);




			return "dashboard";
		} else {
			model.addAttribute("errorMessage", "Login failed. Please check your email and password.");
			return "employeelogin";
		}
	}

	@GetMapping("/dashboard")
	public String getDashboard(Model model) 
	{

		EmployeeEntity e=(EmployeeEntity) session.getAttribute("loggedInEmployee");
		
		Long employeeCount = employeeService.employeeCount();
		Long careerCount = careerService.careerCount();
		Long getTouchCount = getTouchService.getTouchCount();
		Long leavesAppliedCount=leavesService.getAppliedLeaveCount(e.getEmail());
		Long leavesApprovedCount=leavesService.getApprovedLeaveCount(e.getEmail());


//		System.out.println(employeeCount);

		model.addAttribute("employeeCount", employeeCount);
		model.addAttribute("careerCount", careerCount);
		model.addAttribute("getTouchCount", getTouchCount);
		model.addAttribute("leavesAppliedCount",leavesAppliedCount);
		model.addAttribute("leavesApprovedCount",leavesApprovedCount);

		return "dashboard";
	}

	@PostMapping("getTouch")
	public String submitGetTouchForm(@ModelAttribute("data") GetTouch data, Model model) {

		GetTouch getData = getTouchService.findByEmail(data.getEmail());

		if (getData != null) {
			model.addAttribute("msg", "Already Prevously Form Submitted,Pls add or Update Details at Dss/careers");
			model.addAttribute("style", "text-warning text-larger");

			return "index";

		}
		GetTouch added = getTouchService.addForm(data);

		if (added != null) {
			HttpStatus codes = getTouchService.sendEmail(data.getEmail(), data.getName());

			if (codes.value() == 200) {
				model.addAttribute("email", "Check Email !");
			}

			model.addAttribute("msg", "Form submitted successfully");
			model.addAttribute("style", "text-success text-larger");

			return "index";

		}

		model.addAttribute("msg", "Form submission unsuccessfull");
		model.addAttribute("style", "text-danger text-bold text-larger");

		return "index";
	}

	@PostMapping("/career")
	public String submitCareerForm(@ModelAttribute("career") Career career, @RequestParam("file") MultipartFile file,
			Model model) throws IOException {

		File myFile = new File(FILE_DIRECTORY + file.getOriginalFilename());
		myFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(myFile);
		fos.write(file.getBytes());
		fos.close();

		career.setResumePath(file.getOriginalFilename());

		Career submittedCareerForm = careerService.addCareerForm(career);

		if (submittedCareerForm != null) {

			HttpStatus code = careerService.sendEmail(career.getEmail(), career.getName());

			if (code.value() == 200) {
				model.addAttribute("email", "Check Email !");
			}

			model.addAttribute("msg", "form submission successfull");
			model.addAttribute("style", "text-success");

			return "careers";
		}

//        System.out.println(career.toString()); verification

		model.addAttribute("msg", "form submission unsuccessfull");
		model.addAttribute("style", "text-danger");

		return "careers";
	}

	@GetMapping("/forgotPassword")
	public String getForgotPassword() {
		return "forgot";
	}

	@PostMapping("/forgotPassword")
	public String postForgetPassword(@RequestParam("email") String email, Model model) {
		String generatedOtp = employeeService.generateOtp();

		System.out.println(generatedOtp);

		EmployeeEntity emp = employeeService.findEmail(email);

		if (emp != null) {
			HttpStatus code = employeeService.sendEmail(email, generatedOtp);

			if (code.value() == 200) {
				model.addAttribute("email", "Check Email !");
			}

			session.setAttribute("generatedOtp", generatedOtp);
			session.setAttribute("generatedEmail", email);

			model.addAttribute("msg", "Otp Sent SuccessFully");
			model.addAttribute("style", "text-success");

			return "forgotOtp";

		}

		model.addAttribute("msg", "Not having an Account withis Email In Dss");

		return "forgot";

	}

	@PostMapping("/otpVerify")
	public String verifyOtp(@RequestParam("otp") String otp, Model model) {

		String gotp = (String) session.getAttribute("generatedOtp");
		String email;

		if (otp.equals(gotp)) {
			email = (String) session.getAttribute("generatedEmail");

			model.addAttribute("email", "email");

			session.removeAttribute("generatedOtp");

			return "changepassword";
		}

		session.removeAttribute("generatedOtp");
		session.removeAttribute("generatedEmail");

		model.addAttribute("msg", "Invalid Otp");

		return "forgot";

	}

	@PostMapping("/changePassword")
	public String changePassword(@RequestParam("password") String password, Model model) {

		String email = (String) session.getAttribute("generatedEmail");

		employeeService.updatePassword(email, password);

		session.removeAttribute("generatedEmail");

		model.addAttribute("msg", "Updated Successfully");

		return "employeelogin";

	}

}
