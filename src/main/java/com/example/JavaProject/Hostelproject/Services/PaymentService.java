package com.example.JavaProject.Hostelproject.Services;

import com.example.JavaProject.Hostelproject.Dto.PaymentDto;
import com.example.JavaProject.Hostelproject.ResponseHandeler.MsgDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
public class PaymentService {
    public MsgDto  savePayment(PaymentDto paymentDto, MultipartFile image){
        log.info("PaymentDto is {}",paymentDto);
        log.info("ImagePart is{}",image);
        MsgDto msgDto =  new MsgDto();
        msgDto.setMessage("Payment will be updated after Verfication , Stay happy and calm");
        return msgDto;
    }
}
