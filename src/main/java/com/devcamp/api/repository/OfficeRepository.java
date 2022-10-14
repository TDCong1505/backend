package com.devcamp.api.repository;

import com.devcamp.api.model.Office;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OfficeRepository extends JpaRepository<Office,Integer>{
    
}
