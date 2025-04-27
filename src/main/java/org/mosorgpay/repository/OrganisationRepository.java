package org.mosorgpay.repository;

import java.util.Optional;


import org.mosorgpay.model.Organisation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganisationRepository extends JpaRepository<Organisation, String> {
	
	Optional<Organisation> findById(String organisationCode);
}
