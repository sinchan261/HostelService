package com.example.JavaProject.Hostelproject.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String guardianName;
    private String collegeName;

    @OneToMany(mappedBy="student",cascade= {CascadeType.REMOVE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    private List<MobileEntity> mobileNo = new ArrayList<>();
    @OneToOne
    private UserEntity users;
    private String roomNo;
    private String pgLocation;

    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
