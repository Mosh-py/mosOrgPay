package org.mosorgpay.repository;

import org.mosorgpay.model.MoneyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoneyRequestRepository extends JpaRepository<MoneyRequest, Long> {

	

}
