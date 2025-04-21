package org.mosorgpay.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("")
	public String getHomePage() {
		return "index";
	}
	
	
	@GetMapping("/organisationSignUp")
	public String getCompanySignUpPage(){
		return "companySignUp";
	}
	
	@GetMapping("/employeeSignUp")
	public String getEmployeeSignUpPage() {
		return "employeeSignUp";
	}
}
