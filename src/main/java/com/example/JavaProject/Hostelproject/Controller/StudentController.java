package com.example.JavaProject.Hostelproject.Controller;

import com.example.JavaProject.Hostelproject.Dto.MobileDto;
import com.example.JavaProject.Hostelproject.Dto.StudentDto;
import com.example.JavaProject.Hostelproject.Entity.MobileEntity;
import com.example.JavaProject.Hostelproject.Entity.StudentEntity;
import com.example.JavaProject.Hostelproject.RepoSitory.StudentRepository;
import com.example.JavaProject.Hostelproject.Services.StudentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/auth/student")
public class StudentController {

  @Autowired
  StudentService studentService;

  @Autowired
    ModelMapper modelMapper;

    @PostMapping("/store")
    public void saveStudent(@RequestBody StudentDto studentDto, HttpServletRequest httpServletRequest){

 String accessToken = httpServletRequest.getHeader("Authorization").split(" ")[1];
        studentService.saveStudent( studentDto,accessToken );

    }
}
