package com.chapaTuBus.webService.subscription;

public class CreatePaymentIntentResponse {
    private String clientSecret;

    public CreatePaymentIntentResponse(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    // Getter
    public String getClientSecret() {
        return clientSecret;
    }
}