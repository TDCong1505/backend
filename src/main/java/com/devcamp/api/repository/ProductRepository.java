package com.devcamp.api.repository;

import com.devcamp.api.model.Product;

import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends JpaRepository<Product,Integer>{
    
    @Query(value = "SELECT * FROM products WHERE product_name LIKE :productName%",nativeQuery = true)
    Product findByProductName(@Param("productName") String productName);

    @Query(value = "SELECT * FROM products WHERE product_code LIKE :productCode%",nativeQuery = true)
    Product findByProductCode(@Param("productCode") String productCode);
    
}
