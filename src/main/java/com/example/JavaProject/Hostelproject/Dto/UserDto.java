package com.example.JavaProject.Hostelproject.Dto;

import com.example.JavaProject.Hostelproject.Enum.UserTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class UserDto {
    public UserDto(Long id, String name, String email, UserTypeEnum userRole, LocalDateTime createdTimeStamp) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.userRole = userRole;
        this.createdTimeStamp = createdTimeStamp;
    }

    private Long id;
    private  String name;

    private String email;
    private UserTypeEnum userRole;
    private LocalDateTime createdTimeStamp;
}
