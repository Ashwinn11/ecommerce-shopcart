package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.AddCartDto;
import com.ecommerce.shopcart.exceptions.CustomException;
import com.ecommerce.shopcart.model.Cart;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.CartRepository;
import com.ecommerce.shopcart.response.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;

    public ResponseEntity<ApiResponse> manageCart(AddCartDto addCartDto, User user) {
        Optional<Product> product = productService.listById(addCartDto.getProductId());
        if(product.isEmpty()){
            return new ResponseEntity<>(new ApiResponse(false,"Process could not completed"),HttpStatus.BAD_REQUEST);
        }
        Cart cart = new Cart();
        cart.setUser(user);
        cart.setProduct(product.get());
        cart.setQuantity(addCartDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
        return new ResponseEntity<>(new ApiResponse(true,"Cart created"), HttpStatus.ACCEPTED);
    }
}
