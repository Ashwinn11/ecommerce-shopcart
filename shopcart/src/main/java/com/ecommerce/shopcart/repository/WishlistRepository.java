package com.ecommerce.shopcart.repository;

import com.ecommerce.shopcart.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
    List<Wishlist> findByUserId(Integer userId);
}
