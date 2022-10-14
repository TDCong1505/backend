package com.devcamp.api.repository;

import com.devcamp.api.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
public interface OrderDetailRepository extends JpaRepository<OrderDetail,Integer>{

}

