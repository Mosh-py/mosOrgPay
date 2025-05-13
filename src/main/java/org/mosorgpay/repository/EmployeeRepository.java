package org.mosorgpay.repository;



import java.math.BigDecimal;
import java.util.List;
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
	
	List<Employee> findAllByUsername(String username);
	
	Employee findByUsername(String username);
	@Modifying
	@Query("UPDATE Employee a set a.balance = :balance WHERE a.emailAddress = :email")
	void updateBalance(@Param("balance") BigDecimal balance, @Param("email") String email);
}
