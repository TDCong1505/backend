package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.model.ProductDescription;

public interface ProductDescriptionRepository extends JpaRepository<ProductDescription,Integer>{
    
}
