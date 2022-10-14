package com.devcamp.api.service;

import com.devcamp.api.entity.Token;

public interface TokenService {

    Token createToken(Token token);

    Token findByToken(String token);
}
