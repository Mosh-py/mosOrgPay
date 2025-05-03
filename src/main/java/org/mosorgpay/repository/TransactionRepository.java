package org.mosorgpay.repository;

import java.util.UUID;

import org.mosorgpay.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
	
}
