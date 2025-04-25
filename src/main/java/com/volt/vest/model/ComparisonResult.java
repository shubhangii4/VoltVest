package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Data
public class ComparisonResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal mutualFundFutureValue;
    private BigDecimal loanInterestCost;
    private BigDecimal netSavings;
    private BigDecimal mutualFundGrowth;
    private BigDecimal totalCostIfLiquidated;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "loan_id")
    private Loan loan;
    
    @ManyToOne
    @JoinColumn(name = "mutual_fund_id")
    private MutualFund mutualFund;
} 