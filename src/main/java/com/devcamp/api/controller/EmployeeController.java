package com.devcamp.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.devcamp.api.model.Employee;
import com.devcamp.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/")
public class EmployeeController {
    
    @Autowired
    EmployeeService employeeService;

    //GET BY ID 
    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        try{
            Employee employee = employeeService.getEmployeeById(id);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET ALL 
    @GetMapping("/employees")
    public ResponseEntity<List<Employee>> getAllEmployees(){
        try {
            List<Employee> employees = employeeService.getAllEmployees();
            return new ResponseEntity<>(employees,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST 
    @PostMapping("/employees")
    public ResponseEntity<Object> createEmployee(@Valid @RequestBody Employee pEmployee){
        try {
            Employee employee = employeeService.createEmployee(pEmployee);
            return new ResponseEntity<>(employee,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT
    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> updateProductLine(@Valid @RequestBody Employee pEmployee,@PathVariable("id") int id){
        try {
            Employee employee = employeeService.updateEmployee(pEmployee,id);
            return new ResponseEntity<>(employee,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ALL
    @DeleteMapping("/employees")
    public ResponseEntity<Employee> deleteAllEmployees(){
        try {
            employeeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE BY ID
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Employee> deteleEmployeeById(@PathVariable("id") int id){
        try {
            employeeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
