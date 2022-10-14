package com.devcamp.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductLine;
import com.devcamp.api.model.ProductType;
import com.devcamp.api.repository.ProductTypeRepository;

@Service
public class ProductTypeService {
    
    @Autowired
    ProductTypeRepository productTypeRepository;

    //Lấy tất cả danh sách loại sản phẩm phân trang
    public Page<ProductType> getAllPageable(Integer page){
        Page<ProductType> producttypes = productTypeRepository.findAll(PageRequest.of(page,8));
        return producttypes;
    }
    //Lấy theo code 
    public ProductType getByCode(String productTypeCode){
        ProductType productType = productTypeRepository.findByProductTypeCode(productTypeCode);
        return productType;
    }
    //Lấy tất cả danh sách loại sản phẩm
    public List<ProductType> allProductType(){
        List<ProductType> productTypes = new ArrayList<>();
        productTypeRepository.findAll().forEach(productTypes::add);
        return productTypes;
    }

    //Tạo mới một loại sản phẩm
    public ProductType createProductType(ProductType pProductType){
        ProductType productType = new ProductType();
        productType.setProductTypeCode(pProductType.getProductTypeCode());
        productType.setProductTypeName(pProductType.getProductTypeName());

        ProductType saveProductType = productTypeRepository.save(productType);
        return saveProductType;
    }   

    //Cập nhật lại một loại sản phẩm
    public ProductType updateProductType(ProductType pProductType,int id){
        Optional<ProductType> findProductType = productTypeRepository.findById(id);
        ProductType productTypeData = findProductType.get();

        productTypeData.setProductTypeCode(pProductType.getProductTypeCode());
        productTypeData.setProductTypeName(pProductType.getProductTypeName());

        ProductType saveProductType = productTypeRepository.save(productTypeData);
        return saveProductType;
    } 

    //Cập nhật theo code
    public ProductType updateByCode(ProductType pProductType,String productTypeCode){
        ProductType productType = productTypeRepository.findByProductTypeCode(productTypeCode);
        productType.setProductTypeCode(pProductType.getProductTypeCode());
        productType.setProductTypeName(pProductType.getProductTypeName());
        ProductType saveProductType = productTypeRepository.save(productType);
        return saveProductType;
    }

    //Xoá tất cả 
    public void deleteAll(){
        productTypeRepository.deleteAll();
    }

    //Xoá một bình chọn
    public void deleteById(int id){
        productTypeRepository.deleteById(id);
    }

    //Xoá theo mã 
    public void deleteByCode(String productTypeCode){
        productTypeRepository.deleteByProductTypeCode(productTypeCode);
    }

    //Lấy các hãng sản phẩm theo tên loại sản phẩm 
    public List<ProductLine> getProductLinesByProductTypeName(String productTypeName){
        ProductType findProductType = productTypeRepository.findByProductTypeName(productTypeName);
        List<ProductLine> productLines = findProductType.getProductLines();
        return productLines;
    }
}
