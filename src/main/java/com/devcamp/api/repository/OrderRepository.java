package com.devcamp.api.repository;

import java.util.Date;
import java.util.List;
import com.devcamp.api.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order,Integer>{
    
    @Query(value = "SELECT * FROM orders WHERE status LIKE :status%",nativeQuery = true)
    List<Order> findByStatus(@Param("status") String status);

    @Query(value = "SELECT * FROM orders WHERE customer_id LIKE :customerId%",nativeQuery = true)
    List<Order> findByCustomerId(@Param("customerId") int customerId);
    
    //Lấy theo thời gian
    @Query(value = "SELECT * FROM orders WHERE shipped_date BETWEEN :beginDate AND :endDate",nativeQuery = true)
    List<Order> findByDate(@Param("beginDate") String beginDate , @Param("endDate") String endDate);

}
