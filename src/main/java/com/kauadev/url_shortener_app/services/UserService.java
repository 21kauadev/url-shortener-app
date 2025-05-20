package com.kauadev.url_shortener_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kauadev.url_shortener_app.domain.user.User;
import com.kauadev.url_shortener_app.domain.user.exceptions.UserNotFoundException;
import com.kauadev.url_shortener_app.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return user;
    }

}
