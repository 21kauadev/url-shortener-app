package com.kauadev.url_shortener_app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kauadev.url_shortener_app.domain.user.User;
import com.kauadev.url_shortener_app.domain.user.UserDTO;
import com.kauadev.url_shortener_app.infra.ApiResponse;
import com.kauadev.url_shortener_app.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        ApiResponse<List<User>> response = new ApiResponse<List<User>>(HttpStatus.OK.value(), true,
                "Usuários retornados ", users);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable("id") Long id) {
        User user = userService.getUser(id);
        ApiResponse<User> response = new ApiResponse<User>(HttpStatus.OK.value(), true, "Usuário retornado: ", user);

        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO data) {
        userService.updateUser(id, data);
        ApiResponse<Void> response = new ApiResponse<Void>(HttpStatus.OK.value(), true, "Dados atualizados.", null);

        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUser(id);
        ApiResponse<Void> response = new ApiResponse<Void>(HttpStatus.OK.value(), true,
                "Usuário de id " + id + " deletado.", null);

        return ResponseEntity.ok().body(response);
    }
}
