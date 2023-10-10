package com.kroger.bishalexampleskeleton.repository;

import com.kroger.bishalexampleskeleton.entity.Employee;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Long> {

}
