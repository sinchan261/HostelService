package com.example.JavaProject.Hostelproject.Services;

import com.example.JavaProject.Hostelproject.Dto.SignUpDto;
import com.example.JavaProject.Hostelproject.Dto.UserDto;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.RepoSitory.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class AdminService {

    @Autowired
    UserRepository userRepository;

    public List<UserDto> getAllUser(){

        List<UserDto> allUsers = userRepository.findAllUsersAsDto();
        Collections.sort(allUsers,Comparator.comparing(UserDto::getCreatedTimeStamp));

        return allUsers;

    }
    public void deletedUserById( Long id){
        try{
            userRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    @Transactional
    public  void UpdateUserRole( SignUpDto signUpDto){

        UserEntity userEntity = userRepository.findById(signUpDto.getId()).orElseThrow();
        userEntity.setUserRole(signUpDto.getUserRole());
    }
}
