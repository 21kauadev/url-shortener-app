package com.kauadev.url_shortener_app.domain.user;

// record são uma forma concisa e imutável de declarar uma classe que serve apenas para carregar dados
public record UserDTO(String username, String password, UserRole role, String email) {

}
