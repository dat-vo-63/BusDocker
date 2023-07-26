package com.bus.demo.paypal.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.bus.demo.entity.ApprovalUrlResponse;
import com.bus.demo.entity.Order;

@Service
public class PayPalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    public Order createOrder() {
        HttpHeaders headers = createHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Order> response = restTemplate.exchange(
                "https://api.paypal.com/v2/checkout/orders",
                HttpMethod.POST,
                entity,
                Order.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to create PayPal order");
        }
    }

    public ApprovalUrlResponse getApprovalUrl(String orderId) {
        HttpHeaders headers = createHeaders();
        HttpEntity<?> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<ApprovalUrlResponse> response = restTemplate.exchange(
                "https://api.paypal.com/v2/checkout/orders/" + orderId + "/links",
                HttpMethod.GET,
                entity,
                ApprovalUrlResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Failed to get approval URL from PayPal");
        }
    }

    private HttpHeaders createHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(clientId, clientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);
        return headers;
    }
}
