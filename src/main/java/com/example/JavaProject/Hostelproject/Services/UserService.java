package com.example.JavaProject.Hostelproject.Services;

import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.RepoSitory.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
         return  userRepository.findByEmail(username).orElseThrow(()->new NoSuchElementException("Resource not found "));
    }

    public UserEntity getUserById( Long id ){
        try {
           return  userRepository.findById(id).orElseThrow(()->   new NoSuchElementException("No element found"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
