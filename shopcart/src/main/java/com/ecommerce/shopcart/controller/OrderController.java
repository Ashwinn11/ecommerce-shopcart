package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.OrderDTO;
import com.ecommerce.shopcart.response.StripeResponse;
import com.ecommerce.shopcart.service.OrderService;
import com.ecommerce.shopcart.service.TokenService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private OrderService orderService;

    @PostMapping("/create-session")
    public ResponseEntity<com.ecommerce.shopcart.response.StripeResponse> checkout(@RequestBody List<OrderDTO> orderDTOList) throws StripeException {
        Session session = orderService.createSession(orderDTOList);
        com.ecommerce.shopcart.response.StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }
}
