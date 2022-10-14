package com.devcamp.api.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_types")
public class ProductType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "Tên loại sản phẩm không được để trống")
    private String productTypeName;

    @NotNull(message = "Mã loại sản phẩm không được để trống")
    @Column(unique = true)
    private String productTypeCode;

    @OneToMany(mappedBy = "productType")
    @JsonIgnore
    private List<ProductLine> productLines;

    public ProductType() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductTypeName() {
        return productTypeName;
    }

    public void setProductTypeName(String productTypeName) {
        this.productTypeName = productTypeName;
    }

    public String getProductTypeCode() {
        return productTypeCode;
    }

    public void setProductTypeCode(String productTypeCode) {
        this.productTypeCode = productTypeCode;
    }

    public List<ProductLine> getProductLines() {
        return productLines;
    }

    public void setProductLines(List<ProductLine> productLines) {
        this.productLines = productLines;
    }

}
