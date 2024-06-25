package com.chapaTuBus.webService.subscription;

public class CreatePaymentIntentRequest {
    private Long amount;
    private String currency;

    // Getters y setters
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}