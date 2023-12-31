package com.ecommerce.shopcart.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponse {
    private final boolean status;
    private final String message;
    public String getTimestamp() {
        return LocalDateTime.now().toString();
    }

}
