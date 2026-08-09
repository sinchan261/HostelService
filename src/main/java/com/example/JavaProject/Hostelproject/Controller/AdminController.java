package com.example.JavaProject.Hostelproject.Controller;

import com.example.JavaProject.Hostelproject.Dto.SignUpDto;
import com.example.JavaProject.Hostelproject.Dto.UserDto;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/auth")
@RestController
public class AdminController {
@Autowired
    AdminService adminService;

    @GetMapping("/getAllUsers")
    public ResponseEntity getAllUser(){

        List<UserDto> getData = adminService.getAllUser();
        return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(getData);
    }

    @GetMapping("/deleteById")
    public ResponseEntity<Object> deleteuserById(@RequestParam(name ="id") Long id ){
        adminService.deletedUserById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/changeRole")
    public ResponseEntity<Object> UpdateUserRole(@RequestBody SignUpDto signUpDto){
        adminService.UpdateUserRole(signUpDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

}
