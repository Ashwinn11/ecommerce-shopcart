package com.ecommerce.shopcart.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ProductDto {
    private @NotNull String imageUrl;
    private @NotNull String description;
    private @NotNull double price;
    private @NotNull String name;
    private @NotNull Integer categoryId;
}
