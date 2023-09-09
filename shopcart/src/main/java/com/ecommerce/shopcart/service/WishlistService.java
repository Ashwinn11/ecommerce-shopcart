package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.model.User;
import com.ecommerce.shopcart.model.Wishlist;
import com.ecommerce.shopcart.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {
    @Autowired
    private WishlistRepository wishlistRepository;

    public void createWishlist(Wishlist wishlist) {
        wishlistRepository.save(wishlist);
    }

    public List<Wishlist> display(User user) {
        Integer id = user.getId();
        return wishlistRepository.findByUserId(id);
    }
}
