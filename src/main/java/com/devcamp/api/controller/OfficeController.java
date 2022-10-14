package com.devcamp.api.controller;

import java.util.List;

import com.devcamp.api.model.Office;
import com.devcamp.api.service.OfficeService;

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
public class OfficeController {
    
    @Autowired
    OfficeService officeService;

    //GET BY ID 
    @GetMapping("/offices/{id}")
    public ResponseEntity<Office> getOfficeById(@PathVariable("id") int id){
        try{
            Office office = officeService.getOfficeById(id);
            return new ResponseEntity<>(office,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //GET ALL 
    @GetMapping("/offices")
    public ResponseEntity<List<Office>> getAllOffices(){
        try {
            List<Office> offfices = officeService.getAllOffices();
            return new ResponseEntity<>(offfices,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //POST 
    @PostMapping("/offices")
    public ResponseEntity<Object> createEmployee(@RequestBody Office pOffice){
        try {
            Office office = officeService.createOffice(pOffice);
            return new ResponseEntity<>(office,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //PUT
    @PutMapping("/offices/{id}")
    public ResponseEntity<Object> updateOffice(@RequestBody Office pOffice,@PathVariable("id") int id){
        try {
            Office office = officeService.updateOffice(pOffice,id);
            return new ResponseEntity<>(office,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE ALL
    @DeleteMapping("/offices")
    public ResponseEntity<Office> deleteAllOffices(){
        try {
            officeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //DELETE BY ID
    @DeleteMapping("/offices/{id}")
    public ResponseEntity<Office> deleteOfficeById(@PathVariable("id") int id){
        try {
            officeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
