package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.model.AuthenticationToken;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
