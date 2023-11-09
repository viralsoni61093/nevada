package com.mastercard.nevada.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "Payment Request DTO")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentAuthorizeRequestDTO {

    @ApiModelProperty(value = "Merchant ID", example = "123")
    private Long merchantId;

    @ApiModelProperty(value = "Merchant Category", example = "123")
    private Long merchantCategory;

    @ApiModelProperty(value = "Authorization Code", example = "ABC123XYZ")
    private String authorizationCode;

    @ApiModelProperty(value = "OTP", example = "1234")
    private String otp;

    @ApiModelProperty(value = "User's Name", example = "John Doe")
    private String userName;

    @ApiModelProperty(value = "User's Mobile No", example = "9876543210")
    private String userMobileNo;

    @ApiModelProperty(value = "Merchant Location", example = "12345")
    private Long merchantLocation;

}
