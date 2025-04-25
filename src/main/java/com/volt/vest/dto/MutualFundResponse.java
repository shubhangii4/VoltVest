package com.volt.vest.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Data
public class MutualFundResponse {
    private OverallSummary overallSummary;
    private AssetData eligibleAssetData;
    private AssetData nonEligibleAssetData;
    private Map<String, AssetTypeSummary> assetTypeToSummaryMap;
    private MinimumEligibleLimit minimumEligibleLimit;

    @Data
    public static class OverallSummary {
        private Map<String, Map<String, BigDecimal>> nonEligibleFundsSummary;
        private Map<String, BigDecimal> eligibleFundsSummary;
        private Map<String, BigDecimal> totalFundsSummary;
        private Map<String, BigDecimal> eligibleCreditLimit;
    }

    @Data
    public static class AssetData {
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
        private Map<String, Map<String, BigDecimal>> nonEligibleFundsSummary;
        private Map<String, BigDecimal> eligibleFundsSummary;
        private Map<String, BigDecimal> totalFundsSummary;
        private Map<String, BigDecimal> eligibleCreditLimit;
    }

    @Data
    public static class MinimumEligibleLimit {
        private BigDecimal amount;
        private String nonEligibleDisplayReason;
        private Boolean isEligible;
    }
} 