package com.volt.vest.model;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

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