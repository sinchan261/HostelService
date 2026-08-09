package com.example.JavaProject.Hostelproject.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class Example {
    @GetMapping("/200")
    ResponseEntity<String> response200String(){

        System.out.println("************************************");
        return ResponseEntity.ok().body("200");
    }
    @GetMapping("/500")
    ResponseEntity<String> response500String(){
        return ResponseEntity.internalServerError().build();
    }
}
