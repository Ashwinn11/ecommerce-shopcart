package com.ecommerce.shopcart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AddCartDto {
    private @NotNull Integer productId;
    private @NotNull Integer quantity;
}
