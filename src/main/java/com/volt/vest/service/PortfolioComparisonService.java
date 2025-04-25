package com.volt.vest.service;

import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.dto.portfolio.PortfolioResponse;

import java.math.BigDecimal;

public interface PortfolioComparisonService {
    PortfolioComparisonResponse comparePortfolioScenarios(
        BigDecimal currentPortfolioValue,
        BigDecimal desiredLoanAmount,
        BigDecimal interestRate,
        Integer loanTenureYears,
        BigDecimal expectedAnnualReturn
    );

    BigDecimal calculateTotalPortfolioValue(PortfolioResponse portfolioResponse);
} 