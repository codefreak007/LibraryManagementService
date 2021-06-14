package com.example.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorDetails> bookNotFoundException(BookNotFoundException ex, WebRequest req){
        ErrorDetails detail=new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<ErrorDetails>(detail, HttpStatus.NOT_FOUND);
    }

    //For General Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> bookNotFoundException(Exception ex, WebRequest req){
        ErrorDetails detail=new ErrorDetails(new Date(), ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<ErrorDetails>(detail, HttpStatus.NOT_FOUND);
    }
}
