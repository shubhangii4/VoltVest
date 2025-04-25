package com.volt.vest.service.impl;

import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.service.PortfolioComparisonService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class PortfolioComparisonServiceImpl implements PortfolioComparisonService {

    @Override
    public PortfolioComparisonResponse comparePortfolioScenarios(
        BigDecimal currentPortfolioValue,
        BigDecimal desiredLoanAmount,
        BigDecimal interestRate,
        Integer loanTenureYears,
        BigDecimal expectedAnnualReturn
    ) {
        PortfolioComparisonResponse response = new PortfolioComparisonResponse();
        response.setLoanAmount(desiredLoanAmount);

        // Scenario 1: Selling Portfolio (using compound interest)
        BigDecimal portfolioValueAfterSelling = currentPortfolioValue.subtract(desiredLoanAmount);
        BigDecimal futureValueIfSold = calculateFutureValueWithCompoundInterest(
            portfolioValueAfterSelling,
            expectedAnnualReturn,
            loanTenureYears
        );
        response.setFutureAmountIfMutualFundsSold(futureValueIfSold);

        // Scenario 2: Taking Loan (using simple interest)
        BigDecimal totalRepaymentAmount = calculateTotalRepaymentAmountWithSimpleInterest(
            desiredLoanAmount,
            interestRate,
            loanTenureYears
        );
        BigDecimal interestAmount = totalRepaymentAmount.subtract(desiredLoanAmount);
        response.setInterestOfTheLoan(interestAmount);

        // Calculate final portfolio value with loan (using compound interest)
        BigDecimal finalPortfolioValue = calculateFutureValueWithCompoundInterest(
            currentPortfolioValue,
            expectedAnnualReturn,
            loanTenureYears
        );
        BigDecimal finalValueWithLoan = finalPortfolioValue.subtract(totalRepaymentAmount);
        response.setFutureAmountIfLoanTaken(finalValueWithLoan);

        return response;
    }

    private BigDecimal calculateFutureValueWithCompoundInterest(
        BigDecimal principal,
        BigDecimal annualReturn,
        int years
    ) {
        // Compound Interest Formula: FV = P * (1 + r)^n
        // where P = principal, r = rate, n = number of years
        BigDecimal rate = annualReturn.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal onePlusRate = BigDecimal.ONE.add(rate);
        BigDecimal power = onePlusRate.pow(years);
        return principal.multiply(power).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateTotalRepaymentAmountWithSimpleInterest(
        BigDecimal principal,
        BigDecimal annualInterestRate,
        int years
    ) {
        // Simple Interest Formula: Total Repayment = P + (P * r * t)
        // where P = principal, r = rate, t = time in years
        BigDecimal rate = annualInterestRate.divide(BigDecimal.valueOf(100), 10, RoundingMode.HALF_UP);
        BigDecimal interest = principal.multiply(rate).multiply(BigDecimal.valueOf(years));
        return principal.add(interest).setScale(2, RoundingMode.HALF_UP);
    }
} 