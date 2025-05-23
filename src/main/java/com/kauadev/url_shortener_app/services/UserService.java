package com.kauadev.url_shortener_app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.kauadev.url_shortener_app.domain.user.User;
import com.kauadev.url_shortener_app.domain.user.UserDTO;
import com.kauadev.url_shortener_app.domain.user.exceptions.UserNotFoundException;
import com.kauadev.url_shortener_app.repositories.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> getAllUsers() {
        List<User> users = userRepository.findAll();

        return users;
    }

    public User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        return user;
    }

    public void updateUser(Long id, UserDTO data) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        String hashPassword = passwordEncoder.encode(data.password());

        user.setUsername(data.username());
        user.setPassword(hashPassword);
        user.setEmail(data.email());

        userRepository.save(user);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        userRepository.delete(user);
    }

}
