package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.SignUpDto;
import com.ecommerce.shopcart.response.ResponseDto;
import com.ecommerce.shopcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @PostMapping
    public ResponseDto signup(@RequestBody SignUpDto signUpDto){
        return userService.create(signUpDto);
    }

}
