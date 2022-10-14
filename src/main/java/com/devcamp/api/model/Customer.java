  package com.devcamp.api.model;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "customers")
public class  Customer {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotNull(message = "Tên không được để trống")
	@Column(name = "last_name")
	private String lastName;

	@NotNull(message = "Họ không được để trống")
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "phone_number",unique = true)
	private String phoneNumber;

	private String address;

	private String city;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Vote> votes;

	private String state;
	
	private String avatarLink;
	
	@NotNull(message = "Địa chỉ Email không được trống")
	@Column(unique = true)
	private String email;

	@Column(name = "postal_code")
	private String postalCode;

	private String country;

	@Column(name = "sales_rep_employee_number")
	private int salesRepEmployeeNumber;

	@Column(name = "credit_limit")
	private int creditLimit;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Order> orders;

	@OneToMany(mappedBy = "customer")
	@JsonIgnore
	private List<Payment> payments;

	public Customer() {

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public int getSalesRepEmployeeNumber() {
		return salesRepEmployeeNumber;
	}

	public void setSalesRepEmployeeNumber(int salesRepEmployeeNumber) {
		this.salesRepEmployeeNumber = salesRepEmployeeNumber;
	}

	public int getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}

	public List<Order> getOrders() {
		return orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAvatarLink() {
		return avatarLink;
	}

	public void setAvatarLink(String avatarLink) {
		this.avatarLink = avatarLink;
	}
	
	
}
