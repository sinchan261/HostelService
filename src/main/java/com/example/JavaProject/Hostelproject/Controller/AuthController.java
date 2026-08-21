package com.example.JavaProject.Hostelproject.Controller;

import com.example.JavaProject.Hostelproject.Dto.LoginDto;
import com.example.JavaProject.Hostelproject.Dto.SessionDto;
import com.example.JavaProject.Hostelproject.Dto.SignUpDto;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;

import com.example.JavaProject.Hostelproject.Services.AuthService;
import com.example.JavaProject.Hostelproject.Services.JwtServices;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

import static org.hibernate.internal.util.collections.ArrayHelper.forEach;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;
    @Autowired
    JwtServices jwtServices;
    @GetMapping("/getUser/{id}")
    public ResponseEntity<SignUpDto> getUserById(@PathVariable Long id){
        System.out.println(id);
       SignUpDto user = authService.getuserById(id);
       return new ResponseEntity<>(HttpStatus.FOUND).ok(user);
    }
    @PostMapping("/signup")
    public ResponseEntity<SignUpDto> Signup(@RequestBody SignUpDto signUpDto){
       SignUpDto signup = authService.signUpService(signUpDto);
       return new ResponseEntity<>(HttpStatus.CREATED).ok(signup);
    }

    @PostMapping("/login")
    public ResponseEntity<SessionDto> login(@RequestBody LoginDto loginDto, HttpServletResponse httpServletResponse){
        SessionDto sessionDto = authService.loginService(loginDto,httpServletResponse);
        return new ResponseEntity<>(HttpStatus.CREATED).ok(sessionDto);
    }

    @GetMapping("/refresh")
    public ResponseEntity<SessionDto> refreshToken(HttpServletRequest httpServletRequest){
        try {
            Cookie[] cookie = httpServletRequest.getCookies();
            String refreshToken = Arrays.stream(cookie).filter(e -> "refreshToken".equals(e.getName()))
                    .findFirst().map(e -> e.getValue()).orElseThrow(() -> new AuthenticationServiceException("RefreshToken Not found .."));

               return new ResponseEntity<>(HttpStatus.CREATED).ok(authService.getRefreshToken(refreshToken));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    @GetMapping("/logout")
    public ResponseEntity<Object> logoutMethod(HttpServletRequest httpServletRequest){
        authService.logoutService(httpServletRequest);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

//    @GetMapping("/forgetPassword")
//


}
