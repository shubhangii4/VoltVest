package com.volt.vest.model;
import lombok.Data;

@Data
public class VerifyOtpRequest {
    private String clientRefNo;
    private String leadId;
    private String otp;
    private String otpRef;
    private Long reqId;
} 