package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.api.model.ProductType;

public interface ProductTypeRepository extends JpaRepository<ProductType,Integer>{
    
    @Query(value = "SELECT * FROM product_types WHERE product_type_name LIKE :productTypeName%",nativeQuery = true)
    ProductType findByProductTypeName(@Param("productTypeName") String productTypeName);

    @Query(value = "SELECT * FROM product_types WHERE product_type_code LIKE :productTypeCode%",nativeQuery = true)
    ProductType findByProductTypeCode(@Param("productTypeCode") String productTypeCode);

    ProductType deleteByProductTypeCode(String productTypeCode);
    
}
