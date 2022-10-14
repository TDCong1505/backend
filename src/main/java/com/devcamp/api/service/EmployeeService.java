package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;

import com.devcamp.api.model.Employee;
import com.devcamp.api.repository.EmployeeRepository;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
    
    @Autowired
    EmployeeRepository employeeRepository;

    //GET ALL
    public List<Employee> getAllEmployees(){
        List<Employee> allEmployees = new ArrayList<>();
        employeeRepository.findAll().forEach(allEmployees::add);
        return allEmployees;
    }

    //GET BY ID
    public Employee getEmployeeById(int id){
        Optional<Employee> findEmployee = employeeRepository.findById(id);
        Employee employeeData = findEmployee.get();
        return employeeData;
    }
    
    //POST
    public Employee createEmployee(Employee pEmployee){
        Employee newEmployee = new Employee();
        newEmployee.setLastName(pEmployee.getLastName());
        newEmployee.setFirstName(pEmployee.getFirstName());
        newEmployee.setExtension(pEmployee.getExtension());
        newEmployee.setEmail(pEmployee.getEmail());
        newEmployee.setOfficeCode(pEmployee.getOfficeCode());
        newEmployee.setReportTo(pEmployee.getReportTo());
        newEmployee.setJobTitle(pEmployee.getJobTitle());
        newEmployee.setAddress(pEmployee.getAddress());
        newEmployee.setPhoneNumber(pEmployee.getPhoneNumber());
        newEmployee.setJoinDate(new Date());
        
        Employee saveEmployee  = employeeRepository.save(newEmployee);
        return saveEmployee;
    }

    //PUT 
    public Employee updateEmployee (Employee pEmployee,int id){
        Optional<Employee> findEmployee = employeeRepository.findById(id);
        Employee employeeData = findEmployee.get();
        employeeData.setLastName(pEmployee.getLastName());
        employeeData.setFirstName(pEmployee.getFirstName());
        employeeData.setExtension(pEmployee.getExtension());
        employeeData.setEmail(pEmployee.getEmail());
        employeeData.setOfficeCode(pEmployee.getOfficeCode());
        employeeData.setReportTo(pEmployee.getReportTo());
        employeeData.setJobTitle(pEmployee.getJobTitle());
        employeeData.setAddress(pEmployee.getAddress());
        employeeData.setPhoneNumber(pEmployee.getPhoneNumber());

        Employee saveEmployee  = employeeRepository.save(employeeData);
        return saveEmployee;
    }

    //DELETE ALL
    public void deleteAll(){
        employeeRepository.deleteAll();
    }
    
    //DELETE BY ID
    public void deleteById(int id){
        employeeRepository.deleteById(id);
    }

}
