package com.volt.vest.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import lombok.Data;

@Data
public class Loan {
    private BigDecimal principalAmount;
    private BigDecimal interestRate;
    private Integer tenureInMonths;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal totalInterestPaid;
    private BigDecimal totalAmountPaid;
}