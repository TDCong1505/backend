package com.devcamp.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devcamp.api.entity.Role;
import com.devcamp.api.service.RoleService;

@CrossOrigin
@RequestMapping("/")
@RestController
public class RoleController {
    
    @Autowired
    RoleService roleService;

    //Get all
    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getAllRoles(){
        try {
            List<Role> roles = roleService.getAll();
            return new ResponseEntity<>(roles,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
