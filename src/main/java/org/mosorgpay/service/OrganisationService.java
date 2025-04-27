package org.mosorgpay.service;

import org.mosorgpay.dto.OrganisationDto;
import org.mosorgpay.model.Organisation;
import org.mosorgpay.repository.OrganisationRepository;
import org.mosorgpay.serviceInterface.Service;

@org.springframework.stereotype.Service
public class OrganisationService implements Service {

	
	private final OrganisationRepository repository;
	
	public OrganisationService(OrganisationRepository repository) {
		this.repository = repository;
	}
	@Override
	public String register(Object obj) {
		OrganisationDto dto = (OrganisationDto) obj;
		Organisation organisation = new Organisation();
		
		organisation.setCompanyCode(dto.getOrganisationCode());
		organisation.setName(dto.getOrganisationName());
		organisation.setPassword(dto.getOrganisationPassword());
		repository.save(organisation);
		                                                                                                                                                                                                                                                                                                                
		// TODO Auto-generated method stub
		return "done";
	}

	@Override
	public String delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
