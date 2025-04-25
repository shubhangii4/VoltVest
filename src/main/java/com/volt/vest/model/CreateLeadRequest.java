package com.volt.vest.model;

import lombok.Data;

@Data
public class CreateLeadRequest {
    private String phoneNumber;
    private String pan;
    private Object leadTrackingDetails;
    private String partnerAccountId;
    private String source;
} 