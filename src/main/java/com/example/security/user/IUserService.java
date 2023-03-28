package com.example.security.user;


import com.example.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;

public interface IUserService extends UserDetailsService {
    Optional<User> findByUsername(String username);
}
