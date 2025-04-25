package com.volt.vest.service;

import com.volt.vest.model.Loan;
import java.math.BigDecimal;
import java.util.List;

public interface LoanService {
    Loan calculateLoanDetails(BigDecimal principal, BigDecimal interestRate, int tenureInMonths);
    BigDecimal calculateSimpleInterest(BigDecimal principal, BigDecimal rate, int timeInMonths);
    List<Loan> getLoanHistory(Long userId);
    BigDecimal calculateTotalInterestPaid(Loan loan);
} 