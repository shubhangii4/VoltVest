package com.volt.vest.service;

import com.volt.vest.model.ComparisonResult;
import com.volt.vest.model.Loan;
import com.volt.vest.model.MutualFund;
import java.math.BigDecimal;

public interface ComparisonService {
    ComparisonResult compareLoanVsLiquidation(Loan loan, MutualFund mutualFund);
    BigDecimal calculateNetSavings(ComparisonResult comparison);
    ComparisonResult compareForNewUser(BigDecimal loanAmount, int tenureInMonths, MutualFund mutualFund);
    ComparisonResult compareForExistingUser(Loan existingLoan, MutualFund mutualFund);
} 