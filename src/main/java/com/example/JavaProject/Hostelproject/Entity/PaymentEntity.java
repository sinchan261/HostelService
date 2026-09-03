package com.example.JavaProject.Hostelproject.Entity;

import com.example.JavaProject.Hostelproject.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Table(
        name = "payment",
        uniqueConstraints ={
                @UniqueConstraint(
                        name = "uk_user_month_year",
                        columnNames ={"user_id","month","year"}
                )
        }
)
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer month;
    private Integer year;
    private LocalDateTime time;
    @Enumerated(value=EnumType.STRING)
    private PaymentStatus paymentStatus;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn( name="user_id",nullable = false)
    private UserEntity userEntity;
    private String imageUrl;
}
