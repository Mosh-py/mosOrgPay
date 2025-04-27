package org.mosorgpay.repository;



import java.util.UUID;

import org.mosorgpay.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {
	
}
