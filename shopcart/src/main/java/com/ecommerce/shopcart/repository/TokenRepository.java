package com.ecommerce.shopcart.repository;

import com.ecommerce.shopcart.model.AuthenticationToken;
import com.ecommerce.shopcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken,Integer> {
    AuthenticationToken findByEmail(User user);
}
