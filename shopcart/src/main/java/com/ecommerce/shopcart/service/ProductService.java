package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.dto.ProductDto;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.repository.CategoryRepository;
import com.ecommerce.shopcart.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public void create(ProductDto product, Category category) {
        Product product1 = new Product();
        product1.setDescription(product.getDescription());
        product1.setImageUrl(product.getImageUrl());
        product1.setCategory(category);
        product1.setName(product.getName());
        product1.setPrice(product.getPrice());
        productRepository.save(product1);
    }

    public List<Product> list() {
        return productRepository.findAll();
    }

    public Optional<Product> listById(Integer id) {
        return productRepository.findById(id);
    }
}
