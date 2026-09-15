package com.example.JavaProject.Hostelproject.Controller;

import com.example.JavaProject.Hostelproject.Dto.PaymentDto;
import com.example.JavaProject.Hostelproject.ResponseHandeler.MsgDto;
import com.example.JavaProject.Hostelproject.Services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/payment")
public class PaymentController {
   @Autowired
    PaymentService paymentService;
    @PostMapping(value = "/save",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Object>  savePost(@RequestPart("payment") PaymentDto paymentDto, @RequestPart("image")MultipartFile image
    ){
        MsgDto response = paymentService.savePayment(paymentDto,image);
       return new ResponseEntity<>(HttpStatus.ACCEPTED).ok(response);
    }
}
