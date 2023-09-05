package com.ecommerce.shopcart.exceptions;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
@AllArgsConstructor
@RequiredArgsConstructor
public class CustomException extends IllegalArgumentException{
    public CustomException(String s) {
        super(s);
    }
}
