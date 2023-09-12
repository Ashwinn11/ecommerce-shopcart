package com.ecommerce.shopcart.dto;
import com.ecommerce.shopcart.model.Cart;
import com.ecommerce.shopcart.model.Product;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class CartItems {
    private Product productDto;
    @JsonIgnore
    private Integer id;
    private Integer quantity;

    public CartItems(Cart cart){
        this.productDto = cart.getProduct();
        this.quantity = cart.getQuantity();
        this.id = cart.getId();
    }

}
