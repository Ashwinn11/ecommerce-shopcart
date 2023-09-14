package com.ecommerce.shopcart.response;

public class StripeResponse {
    private String sessionId;

    public StripeResponse(String id) {
        this.sessionId= id;
    }
}
