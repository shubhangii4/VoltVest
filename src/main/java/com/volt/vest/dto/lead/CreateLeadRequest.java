package com.volt.vest.dto.lead;

import lombok.Data;

@Data
public class CreateLeadRequest {
    private LeadTrackingDetails leadTrackingDetails;
    private String pan;
    private String phoneNumber;
    private String partnerAccountId;
    private String source;

    @Data
    public static class LeadTrackingDetails {
        private String amplitudeDeviceId;
    }
} 