package com.kauadev.url_shortener_app.domain.user.exceptions;

public class MissingAuthentication extends RuntimeException {

    public MissingAuthentication() {
        super("Você precisa estar autenticado para acessar essa funcionalidade.");
    }

    public MissingAuthentication(String msg) {
        super(msg);
    }

}
