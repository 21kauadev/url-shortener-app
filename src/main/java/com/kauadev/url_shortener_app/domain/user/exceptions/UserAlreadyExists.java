package com.kauadev.url_shortener_app.domain.user.exceptions;

public class UserAlreadyExists extends RuntimeException {

    public UserAlreadyExists() {
        super("Usuário já é cadastrado.");
    }

    public UserAlreadyExists(String msg) {
        super(msg);
    }

}
