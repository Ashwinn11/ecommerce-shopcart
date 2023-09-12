package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.AddCartDto;
import com.ecommerce.shopcart.dto.CartDto;
import com.ecommerce.shopcart.dto.CartItems;
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

import java.util.*;

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

    public CartDto getCart(User user) {
        List<Cart> cartList = cartRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<CartItems> cartItems = new ArrayList<>();
        double totalPrice =0;
        for ( Cart cart : cartList){
            CartItems cartItems1 = new CartItems(cart);
            totalPrice +=cartItems1.getQuantity()*cartItems1.getProductDto().getPrice();
            cartItems.add(cartItems1);
        }
        CartDto cartDto = new CartDto();
        cartDto.setTotalPrice(totalPrice);
        cartDto.setCartItems(cartItems);
        return cartDto;

    }

    public void deleteCartItems(User user, Integer id) {
        Optional<Cart> cart = cartRepository.findById(id);
        Cart cartItem = cart.get();
        if (cartItem.getUser()!=user){
            throw new CustomException("Cart item deletion not valid");
        }
        cartRepository.delete(cartItem);

    }
}
