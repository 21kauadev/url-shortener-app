package com.kauadev.url_shortener_app.infra;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kauadev.url_shortener_app.domain.user.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class })
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(threatedError);
    }

    @ExceptionHandler({ JWTCreationException.class })
    private ResponseEntity<RestErrorMessage> jwtCreationHandler(JWTCreationException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatedError);
    }

    @ExceptionHandler({ JWTDecodeException.class })
    private ResponseEntity<RestErrorMessage> jwtDecodeHandler(JWTDecodeException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatedError);
    }

    @ExceptionHandler({ JWTVerificationException.class })
    private ResponseEntity<RestErrorMessage> jwtVerificationHandler(JWTVerificationException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatedError);
    }

    @ExceptionHandler({ AuthenticationException.class })
    private ResponseEntity<RestErrorMessage> authenticationHandler(AuthenticationException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.BAD_REQUEST.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatedError);
    }

    @ExceptionHandler({ Exception.class })
    private ResponseEntity<RestErrorMessage> globalExceptionHandler(Exception exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(threatedError);
    }
}
