package com.volt.vest.dto.portfolio;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PortfolioComparisonRequest {
    private String jwtToken;
    private BigDecimal desiredLoanAmount;
    private Integer loanTenureYears;
} 