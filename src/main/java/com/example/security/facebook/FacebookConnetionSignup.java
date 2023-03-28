package com.example.security.facebook;

import com.example.entity.User;
import com.example.security.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnetionSignup implements ConnectionSignUp {

    @Autowired
    UserRepository userRepository;


// when first login save db
    @Override
    public String execute(Connection<?> connection) {
        User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(RandomStringUtils.randomAlphabetic(8));
        userRepository.save(user);
        return user.getUsername();
    }
}
