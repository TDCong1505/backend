package com.devcamp.api.model;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "order_details")
public class OrderDetail {
    
    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIgnore
	private Order order;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;	

	@Column(name = "quantity_order")
	private int quantityOrder;

	@Column(name = "price_each")
	private BigDecimal priceEach;

	public OrderDetail() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantityOrder() {
		return quantityOrder;
	}

	public void setQuantityOrder(int quantityOrder) {
		this.quantityOrder = quantityOrder;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}

	public void setPriceEach(BigDecimal priceEach) {
		this.priceEach = priceEach;
	}
}
