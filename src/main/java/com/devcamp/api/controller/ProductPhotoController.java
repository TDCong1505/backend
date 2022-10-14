package com.devcamp.api.controller;

import java.io.File;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.devcamp.api.model.ProductPhoto;
import com.devcamp.api.repository.ProductPhotoRepository;
import com.devcamp.api.service.ProductPhotoService;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ProductPhotoController {
    
    @Autowired
    ProductPhotoService productPhotoService;

    //lấy tất cả
    @GetMapping("/productphotos")
    public ResponseEntity<List<ProductPhoto>> allProductPhoto(){
        try {
            List<ProductPhoto> photos = productPhotoService.getAll();
            return new ResponseEntity<>(photos,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy thông tin 1 ảnh 
    @GetMapping("/productphotos/{id}")
    public ResponseEntity<ProductPhoto> getById(@PathVariable("id") Integer id){
        try {
            ProductPhoto photo = productPhotoService.getById(id);
            return new ResponseEntity<>(photo,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //lấy tất cả có phân trang
    @GetMapping("/productphotos/pageable/{page}")
    public ResponseEntity<Page<ProductPhoto>> getAllPageable(@PathVariable("page") Integer page){
        try {
            Page<ProductPhoto> photos = productPhotoService.getAllPageable(page);
            return new ResponseEntity<>(photos,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/productphotos/productCode/{productCode}")
    public ResponseEntity<ProductPhoto> createProductPhoto(@PathVariable("productCode") String productCode,@Valid @RequestBody ProductPhoto pProductPhoto){
        try {
            ProductPhoto photo = productPhotoService.createProductPhotoToProduct(productCode, pProductPhoto);
            return new ResponseEntity<>(photo,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật lại ảnh 
    @PutMapping("/productphotos/{id}")
    public ResponseEntity<ProductPhoto> updatePhoto(@PathVariable("id") Integer id,@Valid @RequestBody ProductPhoto pProductPhoto){
        try {
            ProductPhoto photo = productPhotoService.updateProductPhoto(id, pProductPhoto);
            return new ResponseEntity<>(photo,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("/productphotos/{id}")
    public ResponseEntity<ProductPhoto> deleteProductPhotoById(@PathVariable("id") int id){
        try {
            productPhotoService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
}
