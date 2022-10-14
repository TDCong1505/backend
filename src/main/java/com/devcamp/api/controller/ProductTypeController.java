package com.devcamp.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductLine;
import com.devcamp.api.model.ProductType;
import com.devcamp.api.service.ProductTypeService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProductTypeController {
    
    @Autowired
    ProductTypeService productTypeService;

    //Lấy tất cả
    @GetMapping("/producttypes")
    public ResponseEntity<List<ProductType>> getAllProducts(){
        try {
            List<ProductType> products = productTypeService.allProductType();
            return new ResponseEntity<>(products,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả theo phân trang
    @GetMapping("/producttypes/pageable/{page}")
    public ResponseEntity<Page> getAllPageable(@PathVariable("page") Integer page){
        try {
            Page<ProductType> producttypes = productTypeService.getAllPageable(page);
            return new ResponseEntity<>(producttypes,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    //Lấy theo code
    @GetMapping("/producttypes/productTypeCode/{productTypeCode}")
    public ResponseEntity<ProductType> getByCode(@PathVariable("productTypeCode") String productTypeCode){
        try {
            ProductType productType = productTypeService.getByCode(productTypeCode);
            return new ResponseEntity<>(productType,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Tạo mới một loại sản phẩm
    @PostMapping("/producttypes")
    public ResponseEntity<ProductType> createProductType(@Valid @RequestBody ProductType pProductType){
        try {
            ProductType productType = productTypeService.createProductType(pProductType);
            return new ResponseEntity<>(productType,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật lại một loại sản phẩm
    @PutMapping("/producttypes/{id}")
    public ResponseEntity<ProductType> updateProductType(@Valid @RequestBody ProductType pProductType,@PathVariable("id") int id){
        try {
            ProductType productType = productTypeService.updateProductType(pProductType, id);
            return new ResponseEntity<>(productType,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nahatj theo code
    @PutMapping("/producttypes/productTypeCode/{productTypeCode}")
    public ResponseEntity<ProductType> updateByCode(@Valid @RequestBody ProductType pProductType,@PathVariable("productTypeCode") String productTypeCode){
        try {
            ProductType productType = productTypeService.updateByCode(pProductType, productTypeCode);
            return new ResponseEntity<>(productType,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Xoá tất cả    
    @DeleteMapping("/producttypes")
    public ResponseEntity<ProductType> deleteAllCustomers(){
        try {
            productTypeService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá một sản phẩm theo id
    @DeleteMapping("/producttypes/{id}")
    public ResponseEntity<ProductType> deleteById(@PathVariable("id") int id){
        try {
            productTypeService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Lấy các hãng sản phẩm theo tên loại sản phẩm 
    @GetMapping("/productlines/productTypeName/{productTypeName}")
    public ResponseEntity<List<ProductLine>> getProductLinesByProductTypeName(@PathVariable("productTypeName") String productTypeName){
        try {
            List<ProductLine> productLines = productTypeService.getProductLinesByProductTypeName(productTypeName);
            return new ResponseEntity<>(productLines,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá theo code 
    @DeleteMapping("/producttypes/productTypeCode/{productTypeCode}")
    public ResponseEntity<ProductType> deleteByCode(@PathVariable("productTypeCode") String productTypeCode){
        try {
            productTypeService.deleteByCode(productTypeCode);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
