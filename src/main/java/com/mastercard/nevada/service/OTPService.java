package com.mastercard.nevada.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OTPService {

    private static final int OTP_LENGTH = 4;

    public  String generateOTP() {
        Random random = new Random();
        StringBuilder otp = new StringBuilder();

        for (int i = 0; i < OTP_LENGTH; i++) {
            otp.append(random.nextInt(10));
        }

        return otp.toString();
    }

    public  boolean validateOTP(String otp) {
        return otp != null && otp.matches("\\d{" + OTP_LENGTH + "}");
    }

}
