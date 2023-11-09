package com.mastercard.nevada.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@ApiModel
public class PaymentRequestDTO {
    @ApiModelProperty
    private Long merchantId;
    @ApiModelProperty
    private Long merchantCategory;
    @ApiModelProperty
    private LocalDateTime transactionDatetime;
    @ApiModelProperty
    private BigDecimal authorizedAmount;
    @ApiModelProperty
    private String userName;
    @ApiModelProperty
    private String userMobileNo;
    @ApiModelProperty
    private String userEmail;

    private Long merchantLocation;
}
