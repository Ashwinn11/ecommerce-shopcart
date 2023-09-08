package com.ecommerce.shopcart.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private @NotNull String imageUrl;
    private @NotNull String description;
    private @NotNull double price;
    private @NotNull String name;
    @ManyToOne
    @JsonIgnore
            @JoinColumn(name = "category_id")
    Category category;
}
