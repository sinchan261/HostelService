package com.example.JavaProject.Hostelproject.Dto;

import com.example.JavaProject.Hostelproject.Entity.MobileEntity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String address;
    private String guardianName;
    private String collegeName;
    private List<MobileDto> mobileNo;
    private String RoomNo;
    private String pgLocation;
}
