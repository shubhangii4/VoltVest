package com.volt.vest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class InitiateOtpResponse {
    private Long reqId;
    private String otpRef;
    private String userSubjectReference;
    private String clientRefNo;

    @JsonProperty("leadId")
    private String leadID;
} 