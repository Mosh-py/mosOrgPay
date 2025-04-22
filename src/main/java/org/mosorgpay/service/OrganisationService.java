package org.mosorgpay.service;

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
	public String save(Object obj) {
		Organisation organisation = (Organisation) obj;
		repository.save(organisation);
		                                                                                                                                                                                                                                                                                                                
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Object obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
