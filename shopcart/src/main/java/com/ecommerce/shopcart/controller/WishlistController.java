package com.ecommerce.shopcart.controller;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.model.Wishlist;
import com.ecommerce.shopcart.response.ApiResponse;
import com.ecommerce.shopcart.response.ResponseDto;
import com.ecommerce.shopcart.service.TokenService;
import com.ecommerce.shopcart.service.WishlistService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    private WishlistService wishlistService;
    @Autowired
    private TokenService tokenService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishlist(@RequestBody Product product, @RequestParam("token") String token) {
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);
        Wishlist wishlist = new Wishlist(product,user);
        wishlistService.createWishlist(wishlist);
        return new ResponseEntity<>(new ApiResponse(true,"Wishlist created"), HttpStatus.ACCEPTED);
    }
    @GetMapping("/get-wishlist")
    public List<Wishlist> displayWishlist(@RequestParam String token){
        tokenService.authenticate(token);
        User user = tokenService.getUser(token);
        return wishlistService.display(user);
    }
}
