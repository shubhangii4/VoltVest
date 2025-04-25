package com.volt.vest.controller;

import com.volt.vest.dto.MutualFundResponse;
import com.volt.vest.service.MutualFundApiService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/mutual-funds")
public class MutualFundController {

    private final MutualFundApiService mutualFundApiService;

    public MutualFundController(MutualFundApiService mutualFundApiService) {
        this.mutualFundApiService = mutualFundApiService;
    }

    @GetMapping("/assets")
    public ResponseEntity<MutualFundResponse> getMutualFunds(
            @RequestHeader("Authorization") String authToken,
            @RequestParam("lead-id") String leadId) {
        MutualFundResponse response = mutualFundApiService.fetchMutualFunds(authToken, leadId);
        return ResponseEntity.ok(response);
    }
} 