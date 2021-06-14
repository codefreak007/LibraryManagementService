package com.example.usermain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorDetails> userExceptionHandler(UserNotFoundException ex, WebRequest req){
        ErrorDetails er=new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(er, HttpStatus.NOT_FOUND);
    }

}
