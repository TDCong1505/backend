package com.devcamp.api.service;

import java.util.List;
import java.util.Optional;
import com.devcamp.api.model.Customer;
import com.devcamp.api.repository.CustomerRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    
    @Autowired
    CustomerRepository customerRepository;

    //Lấy tất cả
    public List<Customer> getAllCustomers(){
        List<Customer> allCustomers = new ArrayList<>();
        customerRepository.findAll().forEach(allCustomers::add);
        return allCustomers;
    }   
    
    //Lấy tất cả phân trang
    public Page<Customer> allCustomer(Integer page){
        Page<Customer> allCustomers = customerRepository.findAll(PageRequest.of(page,8));
        return allCustomers;
    }

    //Search
    public List<Customer> searchCustomer(String query){
        List<Customer> customers = customerRepository.searchCustomers(query);
        return customers;
    }
    //Lấy thông tin khách hàng theo email 
    public Customer getCustomerByEmail(String email){
        Customer customer = customerRepository.findByEmail(email);
        return customer;
    }

    //Lấy thông tin khách hàng theo số điện thoại
    public Customer getCustomerByPhoneNumber(String phoneNumber){
        Customer customer  = customerRepository.findByPhoneNumber(phoneNumber);
        return customer;
    }

    //Lấy dannh sách khách hàng theo quốc gia
    public List<Customer> getByCountry(String country){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findByCountry(country).forEach(customers::add);
        return customers;
    }
    
    //Lấy danh sách khách hàng theo thành phô
    public List<Customer> getByCity(String city){
        List<Customer> customers = new ArrayList<>();
        customerRepository.findByCity(city).forEach(customers::add);
        return customers;
    }
    
    //Lấy thông tin khách hàng theo id
    public Customer getById(int id){
        Optional<Customer> findCustomer = customerRepository.findById(id);
        Customer customerData = findCustomer.get();
        return customerData;
    }

    
    //Tạo mới một khách hàng 
    public Customer createCustomer(Customer pCustomer){
        Customer newCustomer = new Customer();
        newCustomer.setLastName(pCustomer.getLastName());
        newCustomer.setFirstName(pCustomer.getFirstName());
        newCustomer.setPhoneNumber(pCustomer.getPhoneNumber());
        newCustomer.setAddress(pCustomer.getAddress());
        newCustomer.setCity(pCustomer.getCity());
        newCustomer.setState(pCustomer.getState());
        newCustomer.setPostalCode(pCustomer.getPostalCode());
        newCustomer.setCountry(pCustomer.getCountry());
        newCustomer.setSalesRepEmployeeNumber(pCustomer.getSalesRepEmployeeNumber());
        newCustomer.setCreditLimit(0);
        newCustomer.setEmail(pCustomer.getEmail());
        newCustomer.setAvatarLink(pCustomer.getAvatarLink());
        
        Customer saveCustomer = customerRepository.save(newCustomer);
        return saveCustomer;
    }

    //Cập nhật thông tin khách hàng 
    public Customer updateCustomer(Customer pCustomer,int id){
        Optional<Customer> findCustomer = customerRepository.findById(id);
        Customer customerData = findCustomer.get();
        customerData.setLastName(pCustomer.getLastName());
        customerData.setFirstName(pCustomer.getFirstName());
        customerData.setPhoneNumber(pCustomer.getPhoneNumber());
        customerData.setAddress(pCustomer.getAddress());
        customerData.setCity(pCustomer.getCity());
        customerData.setState(pCustomer.getState());
        customerData.setPostalCode(pCustomer.getPostalCode());
        customerData.setCountry(pCustomer.getCountry());
        customerData.setSalesRepEmployeeNumber(pCustomer.getSalesRepEmployeeNumber());
        customerData.setEmail(pCustomer.getEmail());
        customerData.setCreditLimit(pCustomer.getCreditLimit());
        customerData.setAvatarLink(pCustomer.getAvatarLink());

        Customer saveCustomer = customerRepository.save(customerData);
        return saveCustomer;
    }

    //Cập nhật lại số điểm tích luỹ cho khách hàng
    public Customer updateCreditLimit(Customer pCustomer,int id){
        Optional<Customer> findCustomer = customerRepository.findById(id);
        Customer customerData = findCustomer.get();
        customerData.setCreditLimit(pCustomer.getCreditLimit());

        Customer saveCustomer = customerRepository.save(customerData);
        return saveCustomer;
    }

    //Xoá tất cả
    public void deleteAll(){
        customerRepository.deleteAll();
    }
    
    //Xoá theo id
    public void deleteById(int id){
        customerRepository.deleteById(id);
    }

}
