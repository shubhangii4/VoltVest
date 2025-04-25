package com.volt.vest.model;

import lombok.Data;

@Data
public class InitiateOtpResponse {
    private Long reqId;
    private String otpRef;
    private String userSubjectReference;
    private String clientRefNo;
} 