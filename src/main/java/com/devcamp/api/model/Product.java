package com.devcamp.api.model;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "products")
public class Product {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Input product code")
	@Size(min = 2, message = "Mã sản phẩm phải có ít nhất 2 ký tự")
	@Column(name = "product_code", unique = true)
	private String productCode;

	@NotNull(message = "Tên sản phẩm không được để trống")
	@Column(name = "product_name")
	private String productName;

	@Column(name = "product_description")
	private String productDescripttion;

	@Column(name = "product_scale")
	private String productScale;
	
	@Column(name = "product_vendor")
	private String productVendor;

	@Min(message = "Số lượng trong kho phải >= 0", value = 0)
	@Column(name = "quantity_in_stock")
	private int quantityInStock;

	@Min(message = "Giá sản phẩm phải >= 0", value = 0)
	@Column(name = "buy_price")
	private BigDecimal buyPrice;

	@ManyToOne
	@JoinColumn(name = "productLine_id")
	@JsonIgnore
	private ProductLine productLine;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<OrderDetail> orderDetails;

	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<ProductPhoto> productPhoto;
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<Vote> votes;

	@OneToOne(mappedBy = "product")
	@JsonIgnore
	private ProductDescription productDescription;

	@NotNull(message = "Ảnh đại diện không được trống")
	private String mainPhotoURL;

	public Product() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductDescripttion() {
		return productDescripttion;
	}

	public void setProductDescripttion(String productDescripttion) {
		this.productDescripttion = productDescripttion;
	}

	public String getProductScale() {
		return productScale;
	}

	public void setProductScale(String productScale) {
		this.productScale = productScale;
	}

	public String getProductVendor() {
		return productVendor;
	}

	public void setProductVendor(String productVendor) {
		this.productVendor = productVendor;
	}

	public int getQuantityInStock() {
		return quantityInStock;
	}

	public void setQuantityInStock(int quantityInStock) {
		this.quantityInStock = quantityInStock;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<ProductPhoto> getProductPhoto() {
		return productPhoto;
	}

	public void setProductPhoto(List<ProductPhoto> productPhoto) {
		this.productPhoto = productPhoto;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public ProductDescription getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(ProductDescription productDescription) {
		this.productDescription = productDescription;
	}

	public ProductLine getProductLine() {
		return productLine;
	}

	public void setProductLine(ProductLine productLine) {
		this.productLine = productLine;
	}

	public String getMainPhotoURL() {
		return mainPhotoURL;
	}

	public void setMainPhotoURL(String mainPhotoURL) {
		this.mainPhotoURL = mainPhotoURL;
	}

	
}

