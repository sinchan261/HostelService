package com.example.JavaProject.Hostelproject.Entity;

import com.example.JavaProject.Hostelproject.Enum.mobileType;
import com.example.JavaProject.Hostelproject.Enum.ownerType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class MobileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id ;
    private String mobileNo;
    @Enumerated(EnumType.STRING)
    private mobileType mobileType;
    @Enumerated(EnumType.STRING)
    private ownerType personType;
    @ManyToOne()
    @JoinColumn(name="student_id")
    private StudentEntity student;
}
