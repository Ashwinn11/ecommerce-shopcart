package com.ecommerce.shopcart.dto;

import lombok.Data;

@Data
public class OrderDTO {
    private String productName;
    private Integer quantity;
    private double totalPrice;
    private Integer productId;
    private Integer userId;
}
