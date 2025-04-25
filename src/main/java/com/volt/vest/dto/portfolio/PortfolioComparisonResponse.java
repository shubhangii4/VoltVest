package com.volt.vest.dto.portfolio;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PortfolioComparisonResponse {
    private BigDecimal futureAmountIfMutualFundsSold;
    private BigDecimal futureAmountIfLoanTaken;
    private BigDecimal interestOfTheLoan;
    private BigDecimal loanAmount;
} 