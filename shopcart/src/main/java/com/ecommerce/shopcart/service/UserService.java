package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.SignUpDto;
import com.ecommerce.shopcart.exceptions.CustomException;
import com.ecommerce.shopcart.repository.UserRepository;
import com.ecommerce.shopcart.response.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    public ResponseDto create(SignUpDto signUpDto) {
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already exists!");
        }
        return new ResponseDto("Success","User created successfully!");
    }
}
