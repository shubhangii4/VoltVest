package com.volt.vest.service;

import com.volt.vest.dto.portfolio.PortfolioComparisonRequest;
import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.model.*;
import com.volt.vest.dto.portfolio.PortfolioResponse;
import org.springframework.web.bind.annotation.RequestBody;

public interface VoltApiService {
    CreateLeadResponse createLead(CreateLeadRequest request);
    InitiateOtpResponse initiateOtp(InitiateOtpRequest request);
    VerifyOtpResponse verifyOtp(VerifyOtpRequest request);
    PortfolioComparisonResponse getAndComparePortfolio(@RequestBody PortfolioComparisonRequest request);
} 