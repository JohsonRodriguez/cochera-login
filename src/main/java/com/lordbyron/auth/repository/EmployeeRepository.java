package com.lordbyron.auth.repository;

import com.lordbyron.auth.model.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    Employee findByUsername(String username);
}
