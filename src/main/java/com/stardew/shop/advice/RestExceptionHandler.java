package com.stardew.shop.advice;

import com.stardew.shop.exception.LoginException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(LoginException.class)
    public ResponseEntity<Object> handleLoginException(HttpServletRequest request, LoginException loginException){
        String errorMessage = "Email or password is incorrect.";

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorMessage);
    }
}
