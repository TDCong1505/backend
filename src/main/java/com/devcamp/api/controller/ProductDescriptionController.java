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

import com.devcamp.api.model.ProductDescription;
import com.devcamp.api.service.ProductDescriptionService;

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProductDescriptionController {
    
    @Autowired
    ProductDescriptionService productDescriptionService;

    //Lấy tất cả
    @GetMapping("/productdescriptions")
    public ResponseEntity<List<ProductDescription>> getAll(){
        try {
            List<ProductDescription> allProductDescription = productDescriptionService.getAll();
            return new ResponseEntity<>(allProductDescription,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Lấy phân trng
    @GetMapping("/productdescriptions/pageable/{page}")
    public ResponseEntity<Page<ProductDescription>> getAllPageable(@PathVariable("page") Integer page){
        try {
            Page<ProductDescription> des = productDescriptionService.getAllPageable(page);
            return new ResponseEntity<>(des,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // Lấy bằng id
    @GetMapping("/productdescriptions/{id}")
    public ResponseEntity<ProductDescription> getById(@PathVariable("id") Integer id){
        try {
            ProductDescription des = productDescriptionService.getById(id);
            return new ResponseEntity<>(des,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tạo mới
    @PostMapping("/productdescriptions/productCode/{productCode}")
    public ResponseEntity<ProductDescription> create(@Valid @RequestBody ProductDescription pProductDescription,@PathVariable("productCode") String productCode){
        try {
            ProductDescription productDescription = productDescriptionService.createProductDescription(pProductDescription, productCode);
            return new ResponseEntity<>(productDescription,HttpStatus.CREATED);
        } catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật lại
    @PutMapping("/productdescriptions/{id}") 
    public ResponseEntity<ProductDescription> updateProductDescription(@PathVariable("id") int id,@Valid @RequestBody ProductDescription pProductDescription){
        try {
            ProductDescription productDescription = productDescriptionService.updateProductDescription(pProductDescription, id);
            return new ResponseEntity<>(productDescription,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá theo id
    @DeleteMapping("/productdescriptions/{id}")
    public ResponseEntity<ProductDescription> deleteById(@PathVariable("id") int id){
        try {
            productDescriptionService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    //Lấy theo mã sản phẩm
    @GetMapping("/productdescriptions/productCode/{productCode}")
    public ResponseEntity<ProductDescription> getByProductCode(@PathVariable("productCode") String productCode){
        try {
            ProductDescription productDescription = productDescriptionService.getByProductCode(productCode);
            return new ResponseEntity<>(productDescription,HttpStatus.OK);
        } catch(Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}

