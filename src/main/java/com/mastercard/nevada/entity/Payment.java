package com.mastercard.nevada.entity;

import com.mastercard.nevada.config.constants.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "merchant_id", nullable = false)
    private Long merchantId;

    @Column(name = "user_mobile_number", nullable = false)
    private String userMobileNumber;

    @Column(name = "user_name", nullable = true)
    private String userName;

    @Column(name = "user_email", nullable = true)
    private String userEmail;

    @Column(name = "authorization_code", length = 10, nullable = false)
    private String authorizationCode;

    @Column(name = "transaction_datetime", nullable = false)
    private LocalDateTime transactionDatetime;

    @Column(name = "authorized_amount", precision = 10, scale = 2, nullable = false)
    private BigDecimal authorizedAmount;

    @Column(name = "status", length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "otp", length = 6, nullable = false)
    private String otp;
}
