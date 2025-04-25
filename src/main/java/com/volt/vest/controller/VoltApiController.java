package com.volt.vest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.volt.vest.dto.portfolio.PortfolioComparisonRequest;
import com.volt.vest.dto.portfolio.PortfolioComparisonResponse;
import com.volt.vest.model.CreateLeadRequest;
import com.volt.vest.model.CreateLeadResponse;
import com.volt.vest.model.CreateOtpRequest;
import com.volt.vest.model.InitiateOtpRequest;
import com.volt.vest.model.InitiateOtpResponse;
import com.volt.vest.model.VerifyOtpRequest;
import com.volt.vest.model.VerifyOtpResponse;
import com.volt.vest.service.VoltApiService;

@RestController
@RequestMapping("/api/volt")
public class VoltApiController {

    private static final Logger logger = LoggerFactory.getLogger(VoltApiController.class);
    private final VoltApiService voltApiService;

    public VoltApiController(VoltApiService voltApiService) {
        this.voltApiService = voltApiService;
        logger.info("VoltApiController initialized with service: {}", voltApiService);
    }

    @CrossOrigin
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        logger.info("Test endpoint hit");
        return ResponseEntity.ok("Test endpoint working!");
    }

    @CrossOrigin
    @PostMapping("/otp/initiate")
    public ResponseEntity<InitiateOtpResponse> intiateOtp(@RequestBody CreateOtpRequest request) {
        logger.info("Received lead creation request: {}", request);

        CreateLeadRequest createLeadRequest = new CreateLeadRequest();
        createLeadRequest.setPan(request.getPan());
        createLeadRequest.setPhoneNumber(request.getMobileNumber());
        
        // Step 1: Create lead
        CreateLeadResponse createLeadResponse = voltApiService.createLead(createLeadRequest);
        logger.info("Lead created successfull: {}", createLeadResponse);
        
        // Step 2: Initiate OTP
        InitiateOtpRequest otpRequest = new InitiateOtpRequest();
        otpRequest.setLeadId(createLeadResponse.getLeadId());
        InitiateOtpResponse otpResponse = voltApiService.initiateOtp(otpRequest);
        logger.info("OTP initiated successfully for leadId: {}", createLeadResponse.getLeadId());
        

        
        return ResponseEntity.ok(otpResponse);
    }

    @CrossOrigin
    @PostMapping("/otp/verify")
    public ResponseEntity<String> verifyOtp(@RequestBody VerifyOtpRequest request) {
        return ResponseEntity.ok(voltApiService.verifyOtp(request).getJwt());
    }

    @CrossOrigin
    @PostMapping("/portfolio")
    public ResponseEntity<PortfolioComparisonResponse> getPortfolio(@RequestBody PortfolioComparisonRequest request) {
        return ResponseEntity.ok(voltApiService.getAndComparePortfolio(request));
    }

//    @PostMapping("/complete-flow")
//    public ResponseEntity<PortfolioResponse> completeFlow(
//            @RequestBody CreateLeadRequest createLeadRequest,
//            @RequestParam String otp) {
//
//        // Step 1: Create Lead
//        CreateLeadResponse leadResponse = voltApiService.createLead(createLeadRequest);
//
//        // Step 2: Initiate OTP
//        InitiateOtpRequest initiateOtpRequest = new InitiateOtpRequest();
//        initiateOtpRequest.setLeadId(leadResponse.getLeadId());
//        InitiateOtpResponse otpResponse = voltApiService.initiateOtp(initiateOtpRequest);
//
//        // Step 3: Verify OTP
//        VerifyOtpRequest verifyOtpRequest = new VerifyOtpRequest();
//        verifyOtpRequest.setLeadId(leadResponse.getLeadId());
//        verifyOtpRequest.setOtp(otp);
//        verifyOtpRequest.setOtpRef(otpResponse.getOtpRef());
//        verifyOtpRequest.setClientRefNo(otpResponse.getClientRefNo());
//        verifyOtpRequest.setReqId(otpResponse.getReqId());
//        VerifyOtpResponse verifyResponse = voltApiService.verifyOtp(verifyOtpRequest);
//
//        if (!"Success".equals(verifyResponse.getStatus())) {
//            throw new RuntimeException("OTP verification failed: " + verifyResponse.getMessage());
//        }
//
//        // Step 4: Get Portfolio
////        return ResponseEntity.ok(voltApiService.getAndComparePortfolio(verifyResponse.getJwt()));
//    }
} 