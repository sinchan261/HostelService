package com.example.JavaProject.Hostelproject.ResponseHandeler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandeler {

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<GlobalError> handelEmployeeExists(Exception e){
        GlobalError globalError= GlobalError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST).Message(e.getMessage()).build();
        return new ResponseEntity<>(globalError,globalError.httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalError> getExceptionError(Exception e){
        GlobalError globalError= GlobalError.builder()
                .httpStatus(HttpStatus.BAD_REQUEST).Message(e.getMessage()).build();
        return new ResponseEntity<>(globalError,globalError.httpStatus);
    }
}
