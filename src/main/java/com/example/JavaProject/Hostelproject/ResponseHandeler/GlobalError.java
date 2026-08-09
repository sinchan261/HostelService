package com.example.JavaProject.Hostelproject.ResponseHandeler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;
@Data
@Builder
public class GlobalError {
    HttpStatus httpStatus;
    String Message;
    List<String> message;
}
