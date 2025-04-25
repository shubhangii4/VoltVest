package com.volt.vest.service;

import com.volt.vest.dto.MutualFundResponse;

public interface MutualFundApiService {
    MutualFundResponse fetchMutualFunds(String authToken, String leadId);
} 