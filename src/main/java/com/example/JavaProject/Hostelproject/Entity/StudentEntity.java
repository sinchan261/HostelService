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
    @Builder.Default
    @OneToMany(mappedBy="student",cascade= {CascadeType.REMOVE, CascadeType.PERSIST},fetch = FetchType.LAZY)
    private List<MobileEntity> mobileNo = new ArrayList<>();
    @OneToOne
    private UserEntity users;
    private String roomNo;
    private String pgLocation;
  public void addMobileNo(MobileEntity mobileEntity){
      this.mobileNo.add(mobileEntity);
      mobileEntity.setStudent(this);
  }
    @CreationTimestamp
    private LocalDateTime creationTimeStamp;
}
