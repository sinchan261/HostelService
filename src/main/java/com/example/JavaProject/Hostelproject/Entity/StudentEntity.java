package com.example.JavaProject.Hostelproject.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String GuardianName;
    private String CollegeName;

    @OneToMany(mappedBy="student")
    private List<MobileEntity> mobileNo;
    private String RoomNo;
    private String PgLocation;
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
