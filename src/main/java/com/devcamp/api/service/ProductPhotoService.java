package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductPhoto;
import com.devcamp.api.repository.ProductPhotoRepository;
import com.devcamp.api.repository.ProductRepository;
import java.util.ArrayList;

@Service
public class ProductPhotoService {
    
    @Autowired
    ProductPhotoRepository productPhotoRepository;

    @Autowired
    ProductRepository productRepository;

    //Lấy tất cả danh sách ảnh
    public List<ProductPhoto> getAll(){
        List<ProductPhoto> photos = new ArrayList<>();
        productPhotoRepository.findAll().forEach(photos::add);
        return photos; 
    }

    //Lấy tất cả có phân trang
    public Page<ProductPhoto> getAllPageable(Integer page){
        Page<ProductPhoto> photos = productPhotoRepository.findAll(PageRequest.of(page,8));
        return photos;
    }
    
    //Lấy ảnh theo id
    public ProductPhoto getById(int id){
        Optional<ProductPhoto> photo = productPhotoRepository.findById(id);
        ProductPhoto photoData = photo.get();
        return photoData;
    }

    //Tạo mới một ảnh cho một sản phẩm theo mã sản phấm
    public ProductPhoto createProductPhotoToProduct(String productCode,ProductPhoto pProductPhoto){
        Product product = productRepository.findByProductCode(productCode);
        ProductPhoto newProductPhoto = new ProductPhoto();
        newProductPhoto.setPhotoURL(pProductPhoto.getPhotoURL());
        newProductPhoto.setProduct(product);

        ProductPhoto saveProductPhoto =  productPhotoRepository.save(newProductPhoto);
        return saveProductPhoto;
    }

    //Cập nhật lại một ảnh 
    public ProductPhoto updateProductPhoto(int id,ProductPhoto pProductPhoto){
        Optional<ProductPhoto> findProductPhoto = productPhotoRepository.findById(id);
        ProductPhoto productPhotoData = findProductPhoto.get();
        productPhotoData.setPhotoURL(pProductPhoto.getPhotoURL());
        
        ProductPhoto saveProductPhoto =  productPhotoRepository.save(productPhotoData);
        return saveProductPhoto; 
    }


    //Xoá tất cả ảnh
    public void deleteAll(){
        productPhotoRepository.deleteAll();
    }

    //Xoá một ảnh theo id
    public void deleteById(int id){
        productPhotoRepository.deleteById(id);
    }
    
}
