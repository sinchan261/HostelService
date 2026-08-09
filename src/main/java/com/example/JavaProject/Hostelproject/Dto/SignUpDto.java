package com.example.JavaProject.Hostelproject.Dto;

import com.example.JavaProject.Hostelproject.Enum.UserTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignUpDto implements Serializable {
    private Long id;
   private  String name;
   private String password;
    private String email;
    private UserTypeEnum userRole;
    private LocalDateTime createdTimeStamp;
}
