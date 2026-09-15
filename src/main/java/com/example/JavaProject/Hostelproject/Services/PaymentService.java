package com.example.JavaProject.Hostelproject.Services;

import com.example.JavaProject.Hostelproject.Dto.PaymentDto;
import com.example.JavaProject.Hostelproject.Entity.PaymentEntity;
import com.example.JavaProject.Hostelproject.Entity.UserEntity;
import com.example.JavaProject.Hostelproject.Enum.PaymentStatus;
import com.example.JavaProject.Hostelproject.RepoSitory.PaymentRepository;
import com.example.JavaProject.Hostelproject.ResponseHandeler.MsgDto;
import jakarta.persistence.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
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
@Slf4j
@Service
public class PaymentService {
    PaymentRepository paymentRepository;
    public MsgDto  savePayment(PaymentDto paymentDto, MultipartFile image){
        String id = UUID.randomUUID().toString();
        PaymentEntity paymentEntity = PaymentEntity.builder().id(id).month(paymentDto.getMonth())
                .year(paymentDto.getYear()).time(LocalDateTime.now())
                .paymentStatus(paymentDto.getPaymentStatus())
                .amount(paymentDto.getAmount())
                .userEntity((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .build();

        paymentRepository.save(paymentEntity);

        log.info("PaymentDto is {}",paymentDto);
        log.info("ImagePart is{}",image);
        MsgDto msgDto =  new MsgDto();
        msgDto.setMessage("Payment will be updated after Verfication , Stay happy and calm");
        return msgDto;
    }
}
//public class PaymentDto {
//    private Integer month;
//    private Integer year;
//    //    private String time ;
//    private PaymentStatus paymentStatus;
//    private BigDecimal amount;
//}
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