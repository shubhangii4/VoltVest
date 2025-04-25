package com.volt.vest.model;

import lombok.Data;

@Data
public class VerifyOtpResponse {
    private String status;
    private String message;
    private String voltErrorCode;
    private String jwt;
} 