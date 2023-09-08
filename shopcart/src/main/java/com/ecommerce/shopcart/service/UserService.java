package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.SignUpDto;
import com.ecommerce.shopcart.dto.SigninDto;
import com.ecommerce.shopcart.exceptions.CustomException;
import com.ecommerce.shopcart.model.AuthenticationToken;
import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.repository.UserRepository;
import com.ecommerce.shopcart.response.ResponseDto;
import jakarta.xml.bind.DatatypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;
    @Transactional
    public ResponseDto create(SignUpDto signUpDto) {
        if(Objects.nonNull(userRepository.findByEmail(signUpDto.getEmail()))){
            throw new CustomException("User already exists!");
        }
        String encrypted = signUpDto.getPassword();
        try{
            encrypted = hashPassword(signUpDto.getPassword());
        } catch(NoSuchAlgorithmException exception){
            exception.printStackTrace();
        }
        User user = new User();
        user.setEmail(signUpDto.getEmail());
        user.setFirstName(signUpDto.getFirstName());
        user.setLastName(signUpDto.getLastName());
        user.setPassword(encrypted);
        userRepository.save(user);
        final AuthenticationToken token = new AuthenticationToken(user);
        tokenService.saveToken(token);
        return new ResponseDto("Success","User created successfully!");
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        return DatatypeConverter.printHexBinary(digest).toUpperCase();
    }

    public ResponseDto validate(SigninDto signinDto) {
        User user = userRepository.findByEmail(signinDto.getEmail());
        if(Objects.isNull(user)){
            throw new CustomException("User not found");
        }

        try {
            if(!user.getPassword().equals(hashPassword(signinDto.getPassword()))){
                throw new CustomException("User not found");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        AuthenticationToken token = tokenService.validateToken(user);
        if(Objects.isNull(token)){
            throw  new CustomException("Token is not available");
        }
      return new ResponseDto("Success","User found");
    }
}
