package com.volt.vest.service;

import com.volt.vest.model.MutualFund;
import java.math.BigDecimal;
import java.util.List;

public interface MutualFundService {
    List<MutualFund> fetchMutualFunds(Long userId);
    MutualFund calculateFutureValue(MutualFund mutualFund, int tenureInMonths);
    BigDecimal calculateCAGR(MutualFund mutualFund);
    List<MutualFund> getEligibleFunds(Long userId, BigDecimal requiredAmount);
} 