package com.kauadev.url_shortener_app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauadev.url_shortener_app.domain.user.LoginDTO;
import com.kauadev.url_shortener_app.domain.user.User;
import com.kauadev.url_shortener_app.domain.user.UserDTO;
import com.kauadev.url_shortener_app.domain.user.exceptions.UserAlreadyExists;
import com.kauadev.url_shortener_app.infra.ApiResponse;
import com.kauadev.url_shortener_app.infra.security.TokenService;
import com.kauadev.url_shortener_app.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private TokenService tokenService;
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<User>> register(@RequestBody UserDTO data) {

        if (userRepository.findByUsername(data.username()) != null) {
            throw new UserAlreadyExists();
        }

        String hashPassword = passwordEncoder.encode(data.password());

        User user = new User();
        user.setUsername(data.username());
        user.setPassword(hashPassword);
        user.setRole(data.role());
        user.setEmail(data.email());

        userRepository.save(user);

        ApiResponse<User> response = new ApiResponse<User>(HttpStatus.OK.value(), true, "Usuário cadastrado.", user);

        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@RequestBody LoginDTO data) {

        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(data.username(),
                data.password());
        Authentication auth = authenticationManager.authenticate(usernamePassword);

        User user = (User) auth.getPrincipal();
        String token = tokenService.generateToken(user);

        ApiResponse<String> response = new ApiResponse<String>(HttpStatus.OK.value(), true, "Usuário autenticado.",
                token);

        return ResponseEntity.ok().body(response);
    }
}
