package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ComparisonResult {
    private BigDecimal mutualFundFutureValue;
    private BigDecimal loanInterestCost;
    private BigDecimal netSavings;
    private BigDecimal mutualFundGrowth;
    private BigDecimal totalCostIfLiquidated;
} 