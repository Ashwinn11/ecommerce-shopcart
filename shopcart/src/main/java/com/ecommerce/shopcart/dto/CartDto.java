package com.ecommerce.shopcart.dto;

import com.ecommerce.shopcart.service.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class CartDto {
    private List<CartItems> cartItems;
    private double totalPrice;
}
