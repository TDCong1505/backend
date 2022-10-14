package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.devcamp.api.model.ProductPhoto;

public interface ProductPhotoRepository extends JpaRepository<ProductPhoto,Integer> {
    
}
