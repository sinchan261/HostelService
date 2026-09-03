package com.example.JavaProject.Hostelproject.Dto;

import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.Enum.PaymentStatus;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentDto {
    private Integer month;
    private Integer year;
//    private String time ;
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
}
//public class PaymentEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Integer month;
//    private Integer year;
//    private LocalDateTime time;
//    @Enumerated(value= EnumType.STRING)
//    private PaymentStatus paymentStatus;
//    private BigDecimal amount;
//    @ManyToOne
//    @JoinColumn( name="user_id",nullable = false)
//    private UserEntity userEntity;
//    private String imageUrl;
//}