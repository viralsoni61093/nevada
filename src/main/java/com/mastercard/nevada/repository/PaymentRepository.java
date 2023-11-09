package com.mastercard.nevada.repository;

import com.mastercard.nevada.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Payment findByAuthorizationCodeAndUserMobileNumber(String authCode, String userMobileNo);
}
