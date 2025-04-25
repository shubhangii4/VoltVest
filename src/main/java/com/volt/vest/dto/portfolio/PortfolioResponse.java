package com.volt.vest.dto.portfolio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class PortfolioResponse {
    private OverallSummary overallSummary;
    
    @JsonProperty("eligibleAssetData")
    private AssetData eligibleAssetData;
    
    @JsonProperty("nonEligibleAssetData")
    private AssetData nonEligibleAssetData;
    
    @JsonProperty("assetTypeToSummaryMap")
    private Map<String, AssetTypeSummary> assetTypeToSummaryMap;
    
    private MinimumEligibleLimit minimumEligibleLimit;

    @Data
    public static class OverallSummary {
        @JsonProperty("nonEligibleFundsSummary")
        private Map<String, Map<String, BigDecimal>> nonEligibleFundsSummary;
        
        @JsonProperty("eligibleFundsSummary")
        private Map<String, BigDecimal> eligibleFundsSummary;
        
        @JsonProperty("totalFundsSummary")
        private Map<String, BigDecimal> totalFundsSummary;
        
        @JsonProperty("eligibleCreditLimit")
        private Map<String, BigDecimal> eligibleCreditLimit;
    }

    @Data
    public static class AssetData {
        @JsonProperty("MUTUAL_FUND")
        private List<MutualFund> MUTUAL_FUND;
    }

    @Data
    public static class MutualFund {
        private String isin;
        private BigDecimal nav;
        private BigDecimal ltv;
        private BigDecimal totalValue;
        private BigDecimal eligibleValue;
        private BigDecimal nonEligibleValue;
        private BigDecimal creditLimit;
        private String schemeName;
        private String schemeCode;
        private String ownershipType;
        private String folioNumber;
        private String amcCode;
        private String amcName;
        private String modeOfHolding;
        private Integer age;
        private String rtaCode;
        private String panType;
        private String email;
        private String mobile;
        private String fundType;
        private BigDecimal freeUnits;
        private BigDecimal blockedUnits;
        private String blockedSubReason;
        private String blockedReason;
    }

    @Data
    public static class AssetTypeSummary {
        @JsonProperty("nonEligibleFundsSummary")
        private Map<String, Map<String, BigDecimal>> nonEligibleFundsSummary;
        
        @JsonProperty("eligibleFundsSummary")
        private Map<String, BigDecimal> eligibleFundsSummary;
        
        @JsonProperty("totalFundsSummary")
        private Map<String, BigDecimal> totalFundsSummary;
        
        @JsonProperty("eligibleCreditLimit")
        private Map<String, BigDecimal> eligibleCreditLimit;
    }

    @Data
    public static class MinimumEligibleLimit {
        private BigDecimal amount;
        private String nonEligibleDisplayReason;
        private Boolean isEligible;
    }
} 