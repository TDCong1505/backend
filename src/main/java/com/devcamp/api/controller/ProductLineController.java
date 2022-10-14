package com.devcamp.api.controller;

import java.util.List;
import javax.validation.Valid;

import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductLine;
import com.devcamp.api.service.ProductLineService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

@RestController
@CrossOrigin
@RequestMapping("/")
public class ProductLineController {
    

    @Autowired
    ProductLineService productLineService;

    //Lấy danh sách sản phẩm của một nhà sản xuất 

    //Lấy tất cả danh sách productlines
    @GetMapping("/productlines")
    public ResponseEntity<List<ProductLine>> getAllProductlines(){
        try {
            List<ProductLine> productlines = productLineService.getAllProductLines();
            return new ResponseEntity<>(productlines,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lay theo phan trang 
    @GetMapping("productlines/pageable/{page}")
    public ResponseEntity <Page> getProductLinePageable(@PathVariable("page") Integer page){
        try {
            Page<ProductLine> productlines = productLineService.getPageable(page);
            return new ResponseEntity<>(productlines,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy thông tin nhà sản xuất theo id
    @GetMapping("/productlines/{id}")
    public ResponseEntity<ProductLine> getProductLineById(@PathVariable("id") int id){
        try{
            ProductLine productline = productLineService.getProductLineById(id);
            return new ResponseEntity<>(productline,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Lấy thông tin nhà sản xuất theo mã nhà sản xuất
    @GetMapping("productlines/productlineCode/{productlineCode}")
    public ResponseEntity<ProductLine> getProductLineByProductLineCode(@PathVariable("productlineCode") String productLineCode){
        try {
            ProductLine productLine = productLineService.getByProductLineCode(productLineCode);
            return new ResponseEntity<>(productLine,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy thông tin nhà sản xuất theo tên nhà sản xuất
    @GetMapping("productlines/productlineName/{productlineName}")
    public ResponseEntity<ProductLine> getProductLineByProductLineName(@PathVariable("productlineName") String productlineName){
        try {
            ProductLine productLine = productLineService.getByProductLineName(productlineName);
            return new ResponseEntity<>(productLine,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tạo mới một nhà sản xuất
    @PostMapping("/productlines/productTypeId/{productTypeId}")
    public ResponseEntity<Object> createProductLine(@Valid @RequestBody ProductLine pProductLine,@PathVariable("productTypeId") int productTypeId){
        try {
            ProductLine productline = productLineService.createProductLine(pProductLine,productTypeId);
            return new ResponseEntity<>(productline,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Chỉnh sửa một nhà sản xuất
    @PutMapping("/productlines/{id}")
    public ResponseEntity<Object> updateProductLine(@Valid @RequestBody ProductLine pProductLine,@PathVariable("id") int id){
        try {
            ProductLine productline = productLineService.updateProductLine(pProductLine,id);
            return new ResponseEntity<>(productline,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Chỉnh sửa theo code 
    @PutMapping("/productlines/productLineCode/{productLineCode}")
    public ResponseEntity<Object> updateByCode(@Valid @RequestBody ProductLine pProductLine,@PathVariable("productLineCode") String productLineCode){
        try {
            ProductLine productLine = productLineService.updateByCode(pProductLine, productLineCode);
            return new ResponseEntity<>(productLine,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá tất cả
    @DeleteMapping("/productlines")
    public ResponseEntity<ProductLine> deleteAllProductLine(){
        try {
            productLineService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá theo id
    @DeleteMapping("/productlines/{id}")
    public ResponseEntity<ProductLine> deleteProductLineById(@PathVariable("id") int id){
        try {
            productLineService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả các sản phẩm theo tên loại sản phẩm
    @GetMapping("/products/productLineCode/{productLineCode}")
    public ResponseEntity<List<Product>> getProductsByProductLineName(@PathVariable("productLineCode") String productLineCode, Integer page){
        try {
            List<Product> products = productLineService.getProductsByProductLineCode(productLineCode);
            return new ResponseEntity<>(products,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
