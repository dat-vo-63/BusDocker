package com.bus.demo.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.bus.demo.entity.Bill;
import com.bus.demo.repo.BillRepo;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.*;
import java.util.*;

@RestController
@CrossOrigin (value = "*")

public class PayPalController {
	@Autowired
	BillRepo billRepo;
	private static Bill bill;
    private final String  BASE = "https://api-m.sandbox.paypal.com";
    private final static Logger LOGGER =  Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private String getAuth(String client_id, String app_secret) {
        String auth = client_id + ":" + app_secret;
        return Base64.getEncoder().encodeToString(auth.getBytes());
    }

    public String generateAccessToken() {
        String auth = this.getAuth(
                "ASKMEdSFnZqdBAf7vuLLSol3SddkBlPm-0BsYE09sC2WvpXFoPp34RAdviCXcle1-3b2-hcrFFdOWIsq",
                "EJFZfFXubUNOCm1SS3AwGD3tZl8nWgs7eyVPr-85huA-7Mogl5E8MB3mRCfM41iri_xRP9-S6I3ky1CW"
        );
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("Authorization", "Basic " + auth);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        HttpEntity<?> request = new HttpEntity<>(requestBody, headers);
        requestBody.add("grant_type", "client_credentials");

        ResponseEntity<String> response = restTemplate.postForEntity(
                BASE +"/v1/oauth2/token",
                request,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.OK) {
            LOGGER.log(Level.INFO, "GET TOKEN: SUCCESSFUL!");
            return new JSONObject(response.getBody()).getString("access_token");
        } else {
            LOGGER.log(Level.SEVERE, "GET TOKEN: FAILED!");
            return "Unavailable to get ACCESS TOKEN, STATUS CODE " + response.getStatusCode();
        }
    }

    @RequestMapping(value="/api/orders/{orderId}/capture", method = RequestMethod.POST)
  
    public Object capturePayment(@PathVariable("orderId") String orderId) {
        String accessToken = generateAccessToken();
        HttpHeaders headers = new HttpHeaders();
        RestTemplate restTemplate = new RestTemplate();

        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<String>(null, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/checkout/orders/" + orderId + "/capture",
                HttpMethod.POST,
                entity,
                Object.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "ORDER CREATED");
            bill.setBillStatus("Paid");
            billRepo.save(bill);
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED CREATING ORDER");
            return "Unavailable to get CREATE AN ORDER, STATUS CODE " + response.getStatusCode();
        }
    }

    @RequestMapping(value="/api/orders/{billId}", method = RequestMethod.POST)

    public Object createOrder(@PathVariable("billId") long billId) {
    	bill = billRepo.findByBillId(billId);
    	if(bill!=null)
    	{
        String accessToken = generateAccessToken();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        headers.add("Content-Type", "application/json");
        headers.add("Accept", "application/json");
        headers.setContentType(MediaType.APPLICATION_JSON);

        //JSON String
        String requestJson = "{\"intent\":\"CAPTURE\",\"purchase_units\":[{\"amount\":{\"currency_code\":\"USD\",\"value\":\""+bill.getTotalPrice()+"\"}}]}";
        HttpEntity<String> entity = new HttpEntity<String>(requestJson, headers);

        ResponseEntity<Object> response = restTemplate.exchange(
                BASE + "/v2/checkout/orders",
                HttpMethod.POST,
                entity,
                Object.class
        );
        

        if (response.getStatusCode() == HttpStatus.CREATED) {
            LOGGER.log(Level.INFO, "ORDER CAPTURE");
           
          
            
            return response.getBody();
        } else {
            LOGGER.log(Level.INFO, "FAILED CAPTURING ORDER");
            return "Unavailable to get CAPTURE ORDER, STATUS CODE " + response.getStatusCode();
        }
    	}
    	else {
			return null;
		}

    }
}