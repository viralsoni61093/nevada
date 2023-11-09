package com.mastercard.nevada.controller;

import com.mastercard.nevada.config.constants.PaymentStatus;
import com.mastercard.nevada.dto.PaymentAuthorizeRequestDTO;
import com.mastercard.nevada.dto.PaymentRequestDTO;
import com.mastercard.nevada.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping
    public ResponseEntity<String> processPayment(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        String authorizationCode = paymentService.processPaymentAuthorization(paymentRequestDTO);
        return new ResponseEntity<>(authorizationCode, HttpStatus.OK);
    }

    @PostMapping("/authorize")
    public ResponseEntity<String> authorize(@RequestBody PaymentAuthorizeRequestDTO paymentAuthorizeRequestDTO) {
        boolean result=paymentService.authorizePayment(paymentAuthorizeRequestDTO);
        if(result){
            return new ResponseEntity<>(PaymentStatus.SUCCESS.toString(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(PaymentStatus.FAILED.toString(), HttpStatus.OK);
        }
    }

}