package com.ggpsgeorge.spring_user_gaming_list.Exceptions;

import java.util.Date;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {
    
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(
        Exception ex, WebRequest request){

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
            );

            return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<ExceptionResponse> handleNoSuchElementException(
        Exception ex, WebRequest request){

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                request.getDescription(false),
                ex.getMessage()
            );

            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
}
