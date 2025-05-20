package com.kauadev.url_shortener_app.infra;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.kauadev.url_shortener_app.domain.user.exceptions.UserNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ UserNotFoundException.class })
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException exception) {
        RestErrorMessage threatedError = new RestErrorMessage(HttpStatus.NOT_FOUND.value(), exception.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(threatedError);
    }

}
