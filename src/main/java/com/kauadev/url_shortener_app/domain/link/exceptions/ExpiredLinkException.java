package com.kauadev.url_shortener_app.domain.link.exceptions;

public class ExpiredLinkException extends RuntimeException {

    public ExpiredLinkException() {
        super("Este link encurtado já expirou. ");
    }

    public ExpiredLinkException(String msg) {
        super(msg);
    }
}
