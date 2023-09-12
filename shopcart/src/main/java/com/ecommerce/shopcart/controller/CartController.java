package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.AddCartDto;
import com.ecommerce.shopcart.dto.CartDto;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.response.ApiResponse;
import com.ecommerce.shopcart.service.CartService;
import com.ecommerce.shopcart.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private TokenService tokenService;
    @PostMapping("/add-items")
    public ResponseEntity<ApiResponse> addCart(@RequestBody AddCartDto addCartDto , @RequestParam String token){
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);
        return cartService.manageCart(addCartDto,user);
    }
    @GetMapping("/list-cart")
    public CartDto getItems(@RequestParam String token){
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);
        return cartService.getCart(user);
    }

}
