package com.volt.vest.controller;

import com.volt.vest.dto.portfolio.PortfolioComparisonRequest;
import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.dto.portfolio.PortfolioResponse;
import com.volt.vest.service.PortfolioComparisonService;
import com.volt.vest.service.VoltApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
public class PortfolioComparisonController {

    private final PortfolioComparisonService portfolioComparisonService;
    private final VoltApiService voltApiService;

    @Autowired
    public PortfolioComparisonController(
        PortfolioComparisonService portfolioComparisonService,
        VoltApiService voltApiService
    ) {
        this.portfolioComparisonService = portfolioComparisonService;
        this.voltApiService = voltApiService;
    }

    @PostMapping("/compare")
    public PortfolioComparisonResponse getAndComparePortfolio(@RequestBody PortfolioComparisonRequest request) {
        // Get portfolio using JWT token
        PortfolioResponse portfolioResponse = voltApiService.getPortfolio(request.getJwtToken());
        
        // Calculate current portfolio value from totalFundsSummary
        BigDecimal currentPortfolioValue = calculateTotalPortfolioValue(portfolioResponse);
        
        // Compare scenarios with fixed interest rate and expected return
        return portfolioComparisonService.comparePortfolioScenarios(
            currentPortfolioValue,
            request.getDesiredLoanAmount(),
            BigDecimal.valueOf(10.49), // Fixed interest rate
            request.getLoanTenureYears(),
            BigDecimal.valueOf(12.0)   // Fixed expected annual return
        );
    }

    public BigDecimal calculateTotalPortfolioValue(PortfolioResponse portfolioResponse) {
        if (portfolioResponse == null || 
            portfolioResponse.getOverallSummary() == null || 
            portfolioResponse.getOverallSummary().getTotalFundsSummary() == null) {
            return BigDecimal.ZERO;
        }

        Map<String, BigDecimal> totalFundsSummary = portfolioResponse.getOverallSummary().getTotalFundsSummary();
        return totalFundsSummary.values().stream()
            .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
} 