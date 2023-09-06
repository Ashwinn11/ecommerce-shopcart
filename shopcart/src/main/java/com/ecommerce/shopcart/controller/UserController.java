package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.SignUpDto;
import com.ecommerce.shopcart.dto.SigninDto;
import com.ecommerce.shopcart.response.ResponseDto;
import com.ecommerce.shopcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    @PostMapping("/create")
    public ResponseDto signup(@RequestBody SignUpDto signUpDto){
        return userService.create(signUpDto);
    }
    @PostMapping("/signin")
    public ResponseDto signin(@RequestBody SigninDto signinDto){
        return userService.validate(signinDto);
    }

}
