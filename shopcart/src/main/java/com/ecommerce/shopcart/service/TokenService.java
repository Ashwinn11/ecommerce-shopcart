package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.exceptions.CustomException;
import com.ecommerce.shopcart.model.AuthenticationToken;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class TokenService{
    @Autowired
    private TokenRepository tokenRepository;

    public AuthenticationToken validateToken(User user) {
        return tokenRepository.findByUser(user);
    }

    public void saveToken(AuthenticationToken token) {
        tokenRepository.save(token);
    }

    public void authenticate(String token) {
        if(Objects.isNull(token)){
            throw new CustomException("Token not present");
        }
        if (Objects.isNull(getUser(token))){
            throw new CustomException("User not found");
        }
    }
    public User getUser(String token) {
        AuthenticationToken authenticationToken =tokenRepository.findByToken(token);
        if (Objects.isNull(authenticationToken)){
            return null;
        }
        return authenticationToken.getUser();
    }
}
