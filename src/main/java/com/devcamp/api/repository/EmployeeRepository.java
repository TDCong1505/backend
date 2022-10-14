package com.devcamp.api.repository;

import com.devcamp.api.model.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends  JpaRepository<Employee,Integer>{
    
}
