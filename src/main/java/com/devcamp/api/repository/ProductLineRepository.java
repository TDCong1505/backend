package com.devcamp.api.repository;

import com.devcamp.api.model.ProductLine;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductLineRepository extends JpaRepository<ProductLine,Integer>{
    
    @Query(value = "SELECT * FROM product_lines WHERE product_line_code LIKE :productLineCode%",nativeQuery = true)
    ProductLine findByProductLineCode(@Param("productLineCode") String productLineCode);

    @Query(value = "SELECT * FROM product_lines WHERE product_line_code LIKE :productLineName%",nativeQuery = true)
    ProductLine findByProductLineName(@Param("productLineName") String productLineName);

}
