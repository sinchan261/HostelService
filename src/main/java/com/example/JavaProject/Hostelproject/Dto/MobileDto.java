package com.example.JavaProject.Hostelproject.Dto;

import com.example.JavaProject.Hostelproject.Entity.StudentEntity;
import com.example.JavaProject.Hostelproject.Enum.mobileType;
import com.example.JavaProject.Hostelproject.Enum.ownerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileDto {
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private mobileType mobileType;
    @Enumerated(EnumType.STRING)
    private ownerType personType;

}

