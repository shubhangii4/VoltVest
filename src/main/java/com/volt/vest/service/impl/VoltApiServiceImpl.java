package com.volt.vest.service.impl;

import com.volt.vest.controller.PortfolioComparisonController;
import com.volt.vest.dto.portfolio.PortfolioComparisonRequest;
import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.model.*;
import com.volt.vest.dto.portfolio.PortfolioResponse;
import com.volt.vest.service.PortfolioComparisonService;
import com.volt.vest.service.VoltApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Service
public class VoltApiServiceImpl implements VoltApiService {

    private static final Logger logger = LoggerFactory.getLogger(VoltApiServiceImpl.class);

    @Value("${volt.api.base-url}")
    private String baseUrl;

    @Autowired
    private final PortfolioComparisonController portfolioComparisonController;

    @Autowired
    private final PortfolioComparisonService portfolioComparisonService;

    private final RestTemplate restTemplate;

    public VoltApiServiceImpl(PortfolioComparisonController portfolioComparisonController, PortfolioComparisonService portfolioComparisonService, RestTemplate restTemplate) {
        this.portfolioComparisonController = portfolioComparisonController;
        this.portfolioComparisonService = portfolioComparisonService;
        this.restTemplate = restTemplate;
    }

    @Override
    public CreateLeadResponse createLead(CreateLeadRequest request) {
        logger.info("Creating lead with request: {}", request);
        
        String createLeadUrl = baseUrl + "/app/customer/leads";
        HttpHeaders headers = createCommonHeaders();
        headers.set("content-type", "application/json");
        headers.set("x-entitytype", "BORROWER");

        HttpEntity<CreateLeadRequest> createLeadEntity = new HttpEntity<>(request, headers);
        ResponseEntity<CreateLeadResponse> createLeadResponse = restTemplate.exchange(
            createLeadUrl,
            HttpMethod.POST,
            createLeadEntity,
            CreateLeadResponse.class
        );

        CreateLeadResponse response = createLeadResponse.getBody();
        logger.info("Lead created successfully with leadId: {}", response.getLeadId());
        return response;
    }

    @Override
    public InitiateOtpResponse initiateOtp(InitiateOtpRequest request) {
        logger.info("Initiating OTP for leadId: {}", request.getLeadId());
        
        String initiateOtpUrl = baseUrl + "/app/mfCentral/lead/init";
        HttpHeaders headers = createCommonHeaders();
        headers.set("content-type", "application/json");
        headers.set("x-entitytype", "BORROWER");

        HttpEntity<InitiateOtpRequest> initiateOtpEntity = new HttpEntity<>(request, headers);
        
        ResponseEntity<InitiateOtpResponse> otpResponse = restTemplate.exchange(
            initiateOtpUrl,
            HttpMethod.POST,
            initiateOtpEntity,
            InitiateOtpResponse.class
        );

        logger.info("OTP initiated successfully for leadId: {}", request.getLeadId());
        return otpResponse.getBody();
    }

    @Override
    public VerifyOtpResponse verifyOtp(VerifyOtpRequest request) {
        String url = baseUrl + "/app/mfCentral/lead/verify";
        
        HttpHeaders headers = createCommonHeaders();
        headers.set("content-type", "application/json");
        headers.set("x-entitytype", "BORROWER");

        HttpEntity<VerifyOtpRequest> entity = new HttpEntity<>(request, headers);
        
        ResponseEntity<VerifyOtpResponse> response = restTemplate.exchange(
            url,
            HttpMethod.POST,
            entity,
            VerifyOtpResponse.class
        );

        return response.getBody();
    }

    @Override
    public PortfolioComparisonResponse getAndComparePortfolio(@RequestBody PortfolioComparisonRequest request) {
        logger.info("Getting portfolio with JWT token: {}", request.getJwtToken());
        String url = baseUrl + "/app/customer/leads/asset/v2";
        
        HttpHeaders headers = createCommonHeaders();
        headers.set("authorization", "Bearer " + request.getJwtToken());
        headers.set("x-entitytype", "BORROWER");

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<PortfolioResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            PortfolioResponse.class
        );

        PortfolioResponse portfolioResponse = response.getBody();
        logger.info("Received portfolio response: {}", portfolioResponse);
        
        if (portfolioResponse != null && portfolioResponse.getEligibleAssetData() != null) {
            logger.info("Eligible Asset Data: {}", portfolioResponse.getEligibleAssetData());
            if (portfolioResponse.getEligibleAssetData().getMUTUAL_FUND() != null) {
                logger.info("Mutual Fund list size: {}", portfolioResponse.getEligibleAssetData().getMUTUAL_FUND().size());
            } else {
                logger.warn("Mutual Fund list is null");
            }
        } else {
            logger.warn("Portfolio response or Eligible Asset Data is null");
        }

//        return portfolioResponse;
        BigDecimal currentPortfolioValue = portfolioComparisonController.calculateTotalPortfolioValue(portfolioResponse);

        // Compare scenarios with fixed interest rate and expected return
        return portfolioComparisonService.comparePortfolioScenarios(
                currentPortfolioValue,
                request.getDesiredLoanAmount(),
                BigDecimal.valueOf(10.49), // Fixed interest rate
                request.getLoanTenureYears(),
                BigDecimal.valueOf(15.0)   // Fixed expected annual return
        );
    }

    private HttpHeaders createCommonHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json, text/plain, */*");
        headers.set("accept-language", "en-GB,en-US;q=0.9,en;q=0.8");
        headers.set("encryption", "false");
        headers.set("origin", "https://staging.voltmoney.in");
        headers.set("priority", "u=1, i");
        headers.set("referer", "https://staging.voltmoney.in/");
        headers.set("sec-ch-ua", "\"Chromium\";v=\"134\", \"Not:A-Brand\";v=\"24\", \"Google Chrome\";v=\"134\"");
        headers.set("sec-ch-ua-mobile", "?0");
        headers.set("sec-ch-ua-platform", "\"macOS\"");
        headers.set("sec-fetch-dest", "empty");
        headers.set("sec-fetch-mode", "cors");
        headers.set("sec-fetch-site", "same-site");
        headers.set("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36");
        headers.set("x-appmode", "INVESTOR_VIEW");
        headers.set("x-appplatform", "VOLT_WEB_APP");
        headers.set("x-devicetype", "Desktop");
        return headers;
    }
} 