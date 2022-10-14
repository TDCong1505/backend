package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;
import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductLine;
import com.devcamp.api.model.ProductType;
import com.devcamp.api.repository.ProductLineRepository;
import com.devcamp.api.repository.ProductTypeRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProductLineService {
    
    @Autowired
    ProductLineRepository productLineRepository;

    @Autowired
    ProductTypeRepository productTypeRepository;

    //Lấy tất cả nhà sản xuất
    public List<ProductLine> getAllProductLines(){
        List<ProductLine> allProductLines = new ArrayList<>();
        productLineRepository.findAll().forEach(allProductLines::add);
        return allProductLines;
    }

    //Lay theo phan trang 
    public Page<ProductLine> getPageable(Integer page){
        Page<ProductLine> productlines = productLineRepository.findAll(PageRequest.of(page,8));
        return productlines;
    }

    //Lấy thông tin nhà sản xuất theo id
    public ProductLine getProductLineById(int id){
        Optional<ProductLine> findProductLine = productLineRepository.findById(id);
        ProductLine productLineData = findProductLine.get();
        return productLineData;
    }

    //Lấy thông tin nhà sản xuất theo mã 
    public ProductLine getByProductLineCode(String productLineCode){
        ProductLine productLine = productLineRepository.findByProductLineCode(productLineCode);
        return productLine;
    }

    //Lấy thông tin nhà sản xuất theo tên 
    public ProductLine getByProductLineName(String productLineName){
        ProductLine productLine = productLineRepository.findByProductLineName(productLineName);
        return productLine;
    }

    //Tạo mới thông tin mã nhà sản xuất
    public ProductLine createProductLine(ProductLine pProductLine,int productTypeId){
        Optional<ProductType> findProductType = productTypeRepository.findById(productTypeId);
        ProductType productTypeData = findProductType.get();

        ProductLine newProductLine = new ProductLine();
        newProductLine.setDescription(pProductLine.getDescription());
        newProductLine.setProductLineName(pProductLine.getProductLineName());
        newProductLine.setProductLineCode(pProductLine.getProductLineCode());
        newProductLine.setPhotoURL(pProductLine.getPhotoURL());
        newProductLine.setProductType(productTypeData);

        ProductLine saveProductLine  = productLineRepository.save(newProductLine);
        return saveProductLine;
    }

    //Cập nhật thông tin mã nhà sản xuất 
    public ProductLine updateProductLine(ProductLine pProductLine,int id){
        Optional<ProductLine> findProductLine = productLineRepository.findById(id);
        ProductLine productDataLine = findProductLine.get();
        productDataLine.setProductLineName(pProductLine.getProductLineName());
        productDataLine.setProductLineCode(pProductLine.getProductLineCode());
        productDataLine.setDescription(pProductLine.getDescription());
        productDataLine.setPhotoURL(pProductLine.getPhotoURL());

        ProductLine saveProductLine = productLineRepository.save(productDataLine);
        return saveProductLine;
    }

    //Cập nhật theo code
    public ProductLine updateByCode(ProductLine pProductLine,String productLineCode){
        ProductLine productLine = productLineRepository.findByProductLineCode(productLineCode);
        productLine.setProductLineName(pProductLine.getProductLineName());
        productLine.setProductLineCode(pProductLine.getProductLineCode());
        productLine.setDescription(pProductLine.getDescription());

        ProductLine saveProductLine = productLineRepository.save(productLine);
        return saveProductLine;
    }

    //Xoá tất cả    
    public void deleteAll(){
        productLineRepository.deleteAll();
    }
    
    //Xoá nhà sản xuất theo id
    public void deleteById(int id){
        productLineRepository.deleteById(id);
    }
    

    //Lấy tất cả các sản phẩm theo mã loại sản phẩm phân trang
    public List<Product> getProductsByProductLineCode(String productLineCode){
        ProductLine productLine = productLineRepository.findByProductLineCode(productLineCode);
        List<Product> products = productLine.getProducts();
        return products;
    }
    
}
