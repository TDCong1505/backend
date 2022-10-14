package com.devcamp.api.controller;

import java.util.List;
import javax.validation.Valid;
import com.devcamp.api.model.OrderDetail;
import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductPhoto;
import com.devcamp.api.service.ProductService;
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

@RestController
@RequestMapping("/")
@CrossOrigin
public class ProductController {
    
    @Autowired
    ProductService productService;

    //Lấy theo phân trang 
    @GetMapping("/products/pageable/{page}")
    public ResponseEntity<Page> getAllPageable(@PathVariable("page") Integer page){
        try {
            Page<Product> products = productService.getAllPageable(page);
            return new ResponseEntity<>(products,HttpStatus.OK);
        } catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả danh sách sản phẩm
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        try {
            List<Product> products = productService.getAll();
            return new ResponseEntity<>(products,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy một sản phẩm theo id
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") int id){
        try{
            Product product = productService.getById(id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy một sản phẩm theo tên sản phẩm
    @GetMapping("/products/productName/{productName}")
    public ResponseEntity<Product> getProductByProductName(@PathVariable("productName") String productName){
        try {
            Product product = productService.getByProductName(productName);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy một sản phẩm theo mã sản phẩm
    @GetMapping("/products/productCode/{productCode}")
    public ResponseEntity<Product> getProductByProductCode(@PathVariable("productCode") String productCode){
        try {
            Product product = productService.getByProducCode(productCode);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Tạo mới một sản phẩm
    @PostMapping("/products/productLineId/{productLineId}")
    public ResponseEntity<Object> createProduct(@Valid @RequestBody Product pProduct,@PathVariable("productLineId") int productLineId){
        try {
            Product product = productService.createProduct(pProduct, productLineId);
            return new ResponseEntity<>(product,HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật một sản phẩm
    @PutMapping("/products/{id}")
    public ResponseEntity<Object> updateProduct(@Valid @RequestBody Product pProduct,@PathVariable("id") int id){
        try {
            Product product = productService.updateProduct(pProduct, id);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Cập nhật một sản phẩm
    @PutMapping("/products/productCode/{productCode}")
    public ResponseEntity<Object> updateProductByCode(@Valid @RequestBody Product pProduct,@PathVariable("productCode") String productCode){
        try {
            Product product = productService.updateProductByCode(pProduct, productCode);
            return new ResponseEntity<>(product,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    //Xoá tất cả
    @DeleteMapping("/products")
    public ResponseEntity<Product> deleteAllCustomers(){
        try {
            productService.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Xoá một sản phẩm theo id
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Product> deleteById(@PathVariable("id") int id){
        try {
            productService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả danh sách ảnh của một sản phẩm theo mã sản phẩm
    @GetMapping("/productphotos/productCode/{productCode}")
    public ResponseEntity<List<ProductPhoto>> getProductPhotosByProductCode(@PathVariable("productCode") String productCode){
        try {
            List<ProductPhoto> photos = productService.getAllProductPhotosOfProductByProductCode(productCode);
            return new ResponseEntity<>(photos,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả danh sách ảnh của một sản phẩm theo tên sản phẩm
    @GetMapping("/productphotos/productName/{productName}")
    public ResponseEntity<List<ProductPhoto>> getProductPhotosByProductName(@PathVariable("productName") String productName){
        try {
            List<ProductPhoto> photos = productService.getAllProductPhotosOfProductByProductCode(productName);
            return new ResponseEntity<>(photos,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả các đơn hàng có mặt sản phẩm theo mã sản phẩm
    @GetMapping("orderdetails/productCode/{productCode}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByProductCode(@PathVariable("productCode") String productCode){
        try {
            List<OrderDetail> orderDetails = productService.getAllOrderDetailsPhotoOfProductByProductCode(productCode);
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Lấy tất cả các đơn hàng có mặt sản phẩm theo tên sản phẩm
    @GetMapping("orderdetails/productName/{productName}")
    public ResponseEntity<List<OrderDetail>> getOrderDetailsByProductName(@PathVariable("productName") String productName){
        try {
            List<OrderDetail> orderDetails = productService.getAllOrderDetailsPhotoOfProductByProductName(productName);
            return new ResponseEntity<>(orderDetails,HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
