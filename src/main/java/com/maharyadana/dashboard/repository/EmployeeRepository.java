package com.maharyadana.dashboard.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maharyadana.dashboard.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
