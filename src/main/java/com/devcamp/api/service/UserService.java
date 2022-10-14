package com.devcamp.api.service;

import com.devcamp.api.entity.User;
import com.devcamp.api.security.UserPrincipal;

public interface UserService {
    User createUser(User user);

    UserPrincipal findByUsername(String username);
}
