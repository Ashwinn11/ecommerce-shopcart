package com.ecommerce.shopcart.repository;

import com.ecommerce.shopcart.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishlistRepository extends JpaRepository<Wishlist,Integer> {
}
