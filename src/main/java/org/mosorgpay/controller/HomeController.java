package org.mosorgpay.controller;

import java.math.BigDecimal;
import java.util.logging.Logger;

import org.mosorgpay.dto.EmployeeDto;
import org.mosorgpay.dto.OrganisationDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.model.Organisation;
import org.mosorgpay.service.EmployeeService;
import org.mosorgpay.service.OrganisationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	private final EmployeeService employeeService;
	private final Logger logger = Logger.getLogger("Home Controller");
	private final OrganisationService organisationService;

	private HomeController(EmployeeService employeeService, OrganisationService organisationService) {
		this.employeeService = employeeService;
		this.organisationService = organisationService;
	}

	@GetMapping("")
	public String getHomePage() {
		return "index";
	}
	
	@GetMapping("/employeeLogin")
	public String getLogIn() {
		return "employeeLogIn.html";
	}

	@GetMapping("/organisationSignUp")
	public String getCompanySignUpPage() {
		return "companySignUp";
	}

	@GetMapping("/employeeSignUp")
	public String getEmployeeSignUpPage() {
		return "employeeSignUp";
	}

	
	@PostMapping("/companySignUp")
	public String submitOrganisationDetails(@ModelAttribute OrganisationDto organisationDto) {

		organisationService.register(organisationDto);
		return "test";
		
	}

	@PostMapping("/employeeSignUp")	
	public String submitEmployeeDetails(@ModelAttribute EmployeeDto employeeDto, Model model) {
		String myResponse = employeeService.register(employeeDto);
		if (myResponse != "done") {
			logger.info(" User does'nt have valid organisation");
			model.addAttribute("message", "Sorry, you have to include the relevant company code");		
			return "employeeSignUp";
		}
		return "test";
	}
	
	@GetMapping("/welcomeEmployee")
	public String getMainPage() {
		return "employeeWelcome";
	}
	
	@GetMapping("/dashboard")
	public String getDashboardPage(Authentication auth, HttpSession session) {
		
		try {
			
			var name = auth.getName();
			
			Employee employee = employeeService.fetchEmployee(name);
			BigDecimal balance = employee.getBalance();
			session.setAttribute("balance", balance);
			session.setAttribute("employee", employee);
			
		} catch (NullPointerException e){
			return "employeeLogIn";
		}
		
		return "employeeWelcome";
	}
	
//	@PostMapping("/employeeLogin")
//	public String returnSuccessful(Authentication auth, Model model) {
//			
//		String something = auth.getName();
//		logger.info(something);
//		model.addAttribute("username", something);
//		return "employeeWelcome";
//	}
}
