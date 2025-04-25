package com.volt.vest.service.impl;

import com.volt.vest.dto.MutualFundResponse;
import com.volt.vest.service.MutualFundApiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MutualFundApiServiceImpl implements MutualFundApiService {

    @Value("${volt.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    public MutualFundApiServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public MutualFundResponse fetchMutualFunds(String authToken, String leadId) {
        String url = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("/app/customer/leads/asset/v2")
                .queryParam("lead-id", leadId)
                .toUriString();
        
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "*/*");
        headers.set("accept-language", "en-GB,en-US;q=0.9,en;q=0.8");
        headers.set("authorization", "Bearer " + authToken);
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

        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        ResponseEntity<MutualFundResponse> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            entity,
            MutualFundResponse.class
        );

        return response.getBody();
    }
} 