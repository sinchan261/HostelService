package com.example.JavaProject.Hostelproject.Entity;

import com.example.JavaProject.Hostelproject.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@Table(
        name = "payment",
        uniqueConstraints ={
                @UniqueConstraint(
                        name = "uk_user_month_year",
                        columnNames ={"user_id","month","year"}
                )
        }
)
@AllArgsConstructor
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
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
