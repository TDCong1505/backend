package com.devcamp.api.repository;

import java.util.List;
import com.devcamp.api.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PaymentRepository extends JpaRepository<Payment,Integer> {
    
    @Query(value = "SELECT * FROM payments WHERE customer_id LIKE :customerId%",nativeQuery = true)
    List<Payment> findByCustomerId(@Param("customerId") int customerId );
}
