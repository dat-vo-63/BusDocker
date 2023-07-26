package com.bus.demo.entity;

import com.paypal.api.payments.Amount;

public class Order {
    private String id;
    private String status;
    private Amount amount;
    // Add any other necessary fields based on the PayPal API response

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }    public void setStatus(String status) {
        this.status = status;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
    