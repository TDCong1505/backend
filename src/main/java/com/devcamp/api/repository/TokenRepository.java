package com.devcamp.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devcamp.api.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Long> {

    Token findByToken(String token);
}
