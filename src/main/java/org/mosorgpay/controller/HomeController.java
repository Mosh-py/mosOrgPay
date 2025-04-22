package org.mosorgpay.controller;

import java.util.logging.Logger;

import org.mosorgpay.dto.OrganisationDto;
import org.mosorgpay.model.Employee;
import org.mosorgpay.model.Organisation;
import org.mosorgpay.service.EmployeeService;
import org.mosorgpay.service.OrganisationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	
	@GetMapping("/organisationSignUp")
	public String getCompanySignUpPage(){
		return "companySignUp";
	}
	
	@GetMapping("/employeeSignUp")
	public String getEmployeeSignUpPage() {
		return "employeeSignUp";
	}
	
	@PostMapping("/employeeSignUp")
	public String submitEmployeeDetails( @RequestParam Employee employee) {
		employeeService.save(employee);
		return " Done Sucessfully ";
	}
	
	@PostMapping("/companySignUp")
	public String submitOrganisayionDetails( @ModelAttribute OrganisationDto organisationDto) {
		
		
		Organisation organisation = new Organisation();
		logger.info(organisationDto.getOrganisationName());
		organisation.setName(organisationDto.getOrganisationName());
		organisation.setPassword(organisationDto.getOrganisationPassword());
		organisation.setCompanyCode(organisationDto.getOrganisationCode());
		
		organisationService.save(organisation);
		return "test";
	}
}
