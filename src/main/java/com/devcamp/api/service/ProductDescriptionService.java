package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.devcamp.api.model.Product;
import com.devcamp.api.model.ProductDescription;
import com.devcamp.api.repository.ProductDescriptionRepository;
import com.devcamp.api.repository.ProductRepository;

import java.util.ArrayList;

@Service
public class ProductDescriptionService {
    
    @Autowired
    ProductDescriptionRepository productDescriptionRepository;

    @Autowired
    ProductRepository productRepository;

    //Lấy tất cả
    public List<ProductDescription> getAll(){
        List<ProductDescription> allProductDescriptions = new ArrayList<>();
        productDescriptionRepository.findAll().forEach(allProductDescriptions::add);
        return allProductDescriptions;
    }

    //Lấy theo id
    public ProductDescription getById(Integer id){
        Optional<ProductDescription> des = productDescriptionRepository.findById(id);
        ProductDescription desData = des.get();
        return desData;
    }
    
    //Lấy tất cả có phân trang
    public Page<ProductDescription> getAllPageable(Integer page){
        Page<ProductDescription> productDescription = productDescriptionRepository.findAll(PageRequest.of(page, 8));
        return productDescription;
    }

    //Lấy theo mã sản phẩm 
    public ProductDescription getByProductCode(String productCode){
        Product product = productRepository.findByProductCode(productCode);
        ProductDescription productDescription = product.getProductDescription();
        return productDescription;
    }

    //Tạo mới vào một sản phẩm theo mã sản phẩm
    public ProductDescription createProductDescription(ProductDescription pProductDescription, String productCode){
        Product product = productRepository.findByProductCode(productCode);
        ProductDescription newProductDescription = new ProductDescription();
        newProductDescription.setChipset(pProductDescription.getChipset());
        newProductDescription.setInches(pProductDescription.getInches());
        newProductDescription.setMemory(pProductDescription.getMemory());
        newProductDescription.setPixels(pProductDescription.getPixels());
        newProductDescription.setScreen(pProductDescription.getScreen());
        newProductDescription.setSize(pProductDescription.getSize());
        newProductDescription.setVideo(pProductDescription.getVideo());
        newProductDescription.setWeight(pProductDescription.getWeight());
        newProductDescription.setCamera1(pProductDescription.getCamera1());
        newProductDescription.setCamera2(pProductDescription.getCamera2());
        newProductDescription.setPin(pProductDescription.getPin());
        newProductDescription.setSim(pProductDescription.getSim());
        newProductDescription.setOS(pProductDescription.getOS());
        newProductDescription.setWlan(pProductDescription.getWlan());
        newProductDescription.setBluetooth(pProductDescription.getBluetooth());
        newProductDescription.setGps(pProductDescription.getGps());
        newProductDescription.setNfc(pProductDescription.getNfc());
        newProductDescription.setSensor(pProductDescription.getSensor());
        newProductDescription.setMemoryCard(pProductDescription.getMemoryCard());

        newProductDescription.setProduct(product);

        ProductDescription saveProductDescription = productDescriptionRepository.save(newProductDescription);
        return saveProductDescription;
    }

    //Cập nhật lại 
    public ProductDescription updateProductDescription(ProductDescription pProductDescription, int id){
        Optional<ProductDescription> findProductDescription = productDescriptionRepository.findById(id);
        ProductDescription productDescriptionData = findProductDescription.get();
        productDescriptionData.setChipset(pProductDescription.getChipset());
        productDescriptionData.setInches(pProductDescription.getInches());
        productDescriptionData.setMemory(pProductDescription.getMemory());
        productDescriptionData.setPixels(pProductDescription.getPixels());
        productDescriptionData.setScreen(pProductDescription.getScreen());
        productDescriptionData.setSize(pProductDescription.getSize());
        productDescriptionData.setVideo(pProductDescription.getVideo());
        productDescriptionData.setWeight(pProductDescription.getWeight());
        productDescriptionData.setCamera1(pProductDescription.getCamera1());
        productDescriptionData.setCamera2(pProductDescription.getCamera2());
        productDescriptionData.setPin(pProductDescription.getPin());
        productDescriptionData.setSim(pProductDescription.getSim());
        productDescriptionData.setOS(pProductDescription.getOS());
        productDescriptionData.setWlan(pProductDescription.getWlan());
        productDescriptionData.setBluetooth(pProductDescription.getBluetooth());
        productDescriptionData.setGps(pProductDescription.getGps());
        productDescriptionData.setNfc(pProductDescription.getNfc());
        productDescriptionData.setSensor(pProductDescription.getSensor());
        productDescriptionData.setMemoryCard(pProductDescription.getMemoryCard());

        ProductDescription saveProductDescription = productDescriptionRepository.save(productDescriptionData);
        return saveProductDescription;
    }

    //Xoá một thông số 
    public void deleteById(int id){
        productDescriptionRepository.deleteById(id);
    }

    //Xoá hết
    public void deleteAll(){
        productDescriptionRepository.deleteAll();
    }

}
