package com.volt.vest.dto.portfolio;

import lombok.Data;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

@Data
public class PortfolioComparisonRequest {

    private String jwtToken;
    @JsonProperty("loanAmount")
    private BigDecimal desiredLoanAmount;
    @JsonProperty("loanTenure")
    private Integer loanTenureYears;
} 