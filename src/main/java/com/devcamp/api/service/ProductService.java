package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.devcamp.api.model.OrderDetail;
import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductLine;
import com.devcamp.api.model.ProductPhoto;
import com.devcamp.api.repository.ProductLineRepository;
import com.devcamp.api.repository.ProductRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductLineRepository productLineRepository;

    //Lấy theo phân trang :
    public Page<Product> getAllPageable(Integer page){
        Page<Product> products = productRepository.findAll(PageRequest.of(page, 8));
        return products;
    }

    //Lấy tất cả danh sách sản phẩm
    public List<Product> getAll(){
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    //Lấy sản phẩm theo id
    public Product getById(int id){
        Optional<Product> findProduct = productRepository.findById(id);
        Product productData = findProduct.get();
        return productData;
    }
    
    //Lấy sản phẩm theo tên sản phẩm 
    public Product getByProductName(String productName){
        Product findProduct = productRepository.findByProductName(productName);
        return findProduct;
    }

    //Lấy sản phẩm theo mã sản phẩm 
    public Product getByProducCode(String productCode){
        Product findProduct = productRepository.findByProductCode(productCode);
        return findProduct;
    }

    //Tạo mới sản phẩm
    public Product createProduct (Product pProduct,int productLineId){
        Optional<ProductLine> findProductLine = productLineRepository.findById(productLineId);
        ProductLine productLineData = findProductLine.get();
        Product newProduct = new Product();
        newProduct.setProductCode(pProduct.getProductCode());
        newProduct.setProductName(pProduct.getProductName());
        newProduct.setProductDescripttion(pProduct.getProductDescripttion());
        newProduct.setProductScale(pProduct.getProductScale());
        newProduct.setProductVendor(pProduct.getProductVendor());
        newProduct.setQuantityInStock(pProduct.getQuantityInStock());
        newProduct.setBuyPrice(pProduct.getBuyPrice());
        newProduct.setProductLine(productLineData);
        newProduct.setMainPhotoURL(pProduct.getMainPhotoURL());
        
        Product saveProduct = productRepository.save(newProduct);
        return saveProduct;
    }

    //Cập nhật sản phẩm
    public Product updateProduct(Product pProduct,int id){
        Optional<Product> findProduct = productRepository.findById(id);
        Product productData = findProduct.get();
        productData.setProductCode(pProduct.getProductCode());
        productData.setProductName(pProduct.getProductName());
        productData.setProductDescripttion(pProduct.getProductDescripttion());
        productData.setProductScale(pProduct.getProductScale());
        productData.setProductVendor(pProduct.getProductVendor());
        productData.setQuantityInStock(pProduct.getQuantityInStock());
        productData.setBuyPrice(pProduct.getBuyPrice());
        productData.setMainPhotoURL(pProduct.getMainPhotoURL());

        Product saveProduct = productRepository.save(productData);
        return saveProduct;
    }


    //Cập nhật sản phẩm theo code
    public Product updateProductByCode(Product pProduct,String productCode){
        Product productData = productRepository.findByProductCode(productCode);
        productData.setProductCode(pProduct.getProductCode());
        productData.setProductName(pProduct.getProductName());
        productData.setProductDescripttion(pProduct.getProductDescripttion());
        productData.setProductScale(pProduct.getProductScale());
        productData.setProductVendor(pProduct.getProductVendor());
        productData.setQuantityInStock(pProduct.getQuantityInStock());
        productData.setBuyPrice(pProduct.getBuyPrice());
        productData.setMainPhotoURL(pProduct.getMainPhotoURL());

        Product saveProduct = productRepository.save(productData);
        return saveProduct;
    }

    //Xoá tất cả sản phẩm   
    public void deleteAll(){
        productRepository.deleteAll();
    }
    
    //Xoá một sản phẩm
    public void deleteById(int id){
        productRepository.deleteById(id);
    }
    

    //Lấy tất cả danh sách ảnh của một sản phẩm theo mã sản phẩm
    public List<ProductPhoto> getAllProductPhotosOfProductByProductCode(String productCode){
        Product findProduct = productRepository.findByProductCode(productCode);
        List<ProductPhoto> photos = findProduct.getProductPhoto();
        return photos;
    }


    //Lấy tất cả danh sách ảnh của một sản phẩm theo tên sản phẩm
    public List<ProductPhoto> getAllProductPhotosOfProductByProductName(String productName){
        Product findProduct = productRepository.findByProductName(productName);
        List<ProductPhoto> photos = findProduct.getProductPhoto();
        return photos;
    }

    //Lấy tất cả các đơn hàng có mặt sản phẩm theo mã sản phẩm
    public List<OrderDetail> getAllOrderDetailsPhotoOfProductByProductCode(String productCode){
        Product findProduct = productRepository.findByProductCode(productCode);
        List<OrderDetail> orderDetails = findProduct.getOrderDetails();
        return orderDetails;
    }

    //Lấy tất cả các đơn hàng có mặt sản phẩm theo tên sản phẩm
    public List<OrderDetail> getAllOrderDetailsPhotoOfProductByProductName(String productName){
        Product findProduct = productRepository.findByProductName(productName);
        List<OrderDetail> orderDetails = findProduct.getOrderDetails();
        return orderDetails;
    }

}
