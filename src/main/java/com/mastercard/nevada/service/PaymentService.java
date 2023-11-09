package com.mastercard.nevada.service;

import com.mastercard.nevada.config.constants.PaymentStatus;
import com.mastercard.nevada.dto.PaymentAuthorizeRequestDTO;
import com.mastercard.nevada.dto.PaymentRequestDTO;
import com.mastercard.nevada.entity.Payment;
import com.mastercard.nevada.helper.PaymentHelper;
import com.mastercard.nevada.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private  final PaymentHelper paymentHelper;
    private final OTPService otpService;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository, PaymentHelper paymentHelper,OTPService otpService) {
        this.paymentRepository = paymentRepository;
        this.paymentHelper = paymentHelper;
        this.otpService=otpService;
    }

    public String processPaymentAuthorization(PaymentRequestDTO paymentRequestDTO) {

        Payment payment = paymentHelper.convertToPayment(paymentRequestDTO);
        String authCode = generateAuthCode(payment);
        generateOTP(payment);
        payment.setStatus(PaymentStatus.PENDING);
        paymentRepository.save(payment);
        return  authCode;
    }
    private Payment getPaymentByAuthCodeAndMobile(String authCode, String userMobileNo) {
        return paymentRepository.findByAuthorizationCodeAndUserMobileNumber(authCode, userMobileNo);
    }
    public boolean authorizePayment(PaymentAuthorizeRequestDTO authorizeRequestDTO) {
        // Retrieve payment record from the database by matching auth code and mobile number
        Payment payment = getPaymentByAuthCodeAndMobile(
                authorizeRequestDTO.getAuthorizationCode(),
                authorizeRequestDTO.getUserMobileNo()
        );

        // If payment record is found, validate OTP
        if (payment != null &&  otpService.validateOTP(authorizeRequestDTO.getOtp())) {

            payment.setStatus(PaymentStatus.SUCCESS);
            paymentRepository.save(payment);
            return  true;
        }
        // If payment record is not found or OTP is invalid, update payment status to FAILED
        if (payment != null) {
            payment.setStatus(PaymentStatus.FAILED);
            paymentRepository.save(payment);
        }


        return false;
    }
    private String generateAuthCode(Payment payment) {
        String authCode=generateAuthorizationCode();
        payment.setAuthorizationCode(authCode);
        return authCode;
    }

    private void generateOTP(Payment payment) {
        String otp=otpService.generateOTP();
        payment.setOtp(otp);
    }

    private  String generateAuthorizationCode() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
