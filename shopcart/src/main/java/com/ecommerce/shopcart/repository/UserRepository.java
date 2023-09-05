package com.ecommerce.shopcart.repository;

import com.ecommerce.shopcart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Object findByEmail(String email);
}
