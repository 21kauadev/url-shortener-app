package com.kauadev.url_shortener_app.domain.user.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        super("Usuário não encontrado.");
    }

    public UserNotFoundException(String msg) {
        super(msg);
    }

}
