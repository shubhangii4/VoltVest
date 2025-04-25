package com.volt.vest.dto.lead;

import lombok.Data;
import java.util.Map;

@Data
public class CreateLeadResponse {
    private String leadId;
    private String phoneNumber;
    private String emailId;
    private String pan;
    private String fullName;
    private String address;
    private String borrowerAccountId;
    private String creditApplicationId;
    private String creditId;
    private String partnerAccountId;
    private String platformAccountId;
    private String source;
    private String leadStatus;
    private String remarks;
    private Map<String, Object> leadMetadata;
    private LeadTrackingDetails leadTrackingDetails;
    private Long addedOnTimeStamp;
    private Long lastUpdatedTimeStamp;
    private Long createdOn;
    private Long lastUpdatedOn;

    @Data
    public static class LeadTrackingDetails {
        private String ipAddress;
        private String deviceId;
        private String deviceType;
        private String city;
        private String state;
        private String utmSource;
        private String utmMedium;
        private String utmCampaign;
        private String utmContent;
        private String utmTerm;
        private String latitude;
        private String longitude;
        private String amplitudeDeviceId;
        private String requestId;
    }
} 