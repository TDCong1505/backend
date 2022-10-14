package com.devcamp.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.devcamp.api.model.Vote;

public interface VoteRepository extends JpaRepository<Vote,Integer>{
    
    @Query(value = "SELECT * FROM votes WHERE customer_id LIKE :customerId%",nativeQuery = true)
    List<Vote> findByCustomerId(@Param("customerId") int customerId);
    
}
