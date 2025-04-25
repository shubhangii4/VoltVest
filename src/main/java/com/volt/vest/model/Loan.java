package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer tenureInMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalInterestPaid;
    private BigDecimal totalAmountPaid;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "mutual_fund_id")
    private MutualFund securedAgainst;
} 