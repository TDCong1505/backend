package com.devcamp.api.repository;

import java.util.List;
import com.devcamp.api.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    
    //Lấy thông tin khách hàng theo số điện thoại
    @Query(value = "SELECT * FROM customers WHERE phone_number LIKE :phoneNumber%",nativeQuery = true)
    Customer findByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    //Lấy danh sách khách hàng theo quốc gia
    @Query(value = "SELECT * FROM customers WHERE country LIKE :country%",nativeQuery = true)
    List<Customer> findByCountry(@Param("country") String country);

    //Lấy danh sách khách hàng theo thành phố
    @Query(value = "SELECT * FROM customers WHERE city LIKE :city%",nativeQuery = true)
    List<Customer> findByCity(@Param("city") String city);
    
    //Lấy thông tin khách hàng theo email\
    @Query(value = "SELECT * FROM customers WHERE email LIKE :email%",nativeQuery = true)
    Customer findByEmail(@Param("email") String email);

    @Query(value = "SELECT * FROM customers WHERE " + "phone_number LIKE CONCAT('%',:query, '%')" + " OR city LIKE CONCAT('%',:query, '%')",nativeQuery = true)
    List<Customer> searchCustomers(String query);
    
}
