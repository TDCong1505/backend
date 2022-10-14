package com.devcamp.api.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product_lines")
public class ProductLine {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Tên nhà sản xuất không được để trống")
	@Size(min = 2, message = "Tên nhà sản xuất phải có ít nhất 2 ký tự")
	@Column(name = "product_line_name", unique = true)
	private String productLineName;

	@NotNull(message = "Mã nhà sản xuất không được để trống")
	@Size(min = 2, message = "Mã nhà sản xuất phải có ít nhất 2 ký tự")
	@Column(name = "product_line_code", unique = true)
	private String productLineCode;

	@ManyToOne
	@JoinColumn(name = "product_type_id")
	private ProductType productType;
	
	@NotEmpty(message = "Phần mô tả không được để trống")
	private String description;

	@OneToMany(mappedBy = "productLine")
	@JsonIgnore
	private List<Product> products;
	
	@NotNull(message = "Ảnh sản phẩm đại diện của hãng không được trống")
	private String photoURL;

	public ProductLine() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProductLineName() {
		return productLineName;
	}

	public void setProductLineName(String productLineName) {
		this.productLineName = productLineName;
	}

	public String getProductLineCode() {
		return productLineCode;
	}

	public void setProductLineCode(String productLineCode) {
		this.productLineCode = productLineCode;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

}
