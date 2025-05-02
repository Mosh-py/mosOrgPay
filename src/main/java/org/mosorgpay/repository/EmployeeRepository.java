package org.mosorgpay.repository;



import java.math.BigDecimal;
import java.util.UUID;

import org.mosorgpay.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
	Employee findByEmailAddress(String email);
	
	@Modifying
	@Query("UPDATE Employee a set a.balance = :balance WHERE a.id = :id ")
	void updateBalance(@Param("balance") BigDecimal balance, @Param("id") String id);
}
