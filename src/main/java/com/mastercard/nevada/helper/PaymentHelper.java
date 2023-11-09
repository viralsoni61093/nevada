package com.mastercard.nevada.helper;

import com.mastercard.nevada.dto.PaymentRequestDTO;
import com.mastercard.nevada.entity.Payment;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PaymentHelper {

    public  Payment convertToPayment(PaymentRequestDTO paymentRequestDTO) {
        Payment payment = new Payment();
        payment.setMerchantId(paymentRequestDTO.getMerchantId());
        payment.setUserName(paymentRequestDTO.getUserName());
        payment.setUserEmail(paymentRequestDTO.getUserEmail());
        payment.setUserMobileNumber(paymentRequestDTO.getUserMobileNo());
        payment.setAuthorizationCode(generateAuthorizationCode());
        payment.setTransactionDatetime(paymentRequestDTO.getTransactionDatetime());
        payment.setAuthorizedAmount(paymentRequestDTO.getAuthorizedAmount());
        return payment;
    }
    private static String generateAuthorizationCode() {
        return UUID.randomUUID().toString().substring(0, 10);
    }
}
