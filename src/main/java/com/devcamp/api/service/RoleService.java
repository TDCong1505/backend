package com.devcamp.api.service;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devcamp.api.entity.Role;
import com.devcamp.api.repository.RoleRepository;

@Service
public class RoleService {
    
    @Autowired
    RoleRepository roleRepository;

    //Get all
    public List<Role> getAll(){
        List<Role> roles = new ArrayList<>();
        roleRepository.findAll().forEach(roles::add);
        return roles;
    }
}
