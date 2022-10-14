package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.entity.Role;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    
}
