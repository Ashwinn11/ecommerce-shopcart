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

    public boolean findById(Integer id){
        return productRepository.findById(id).isPresent();
    }

    public void update(Integer id, ProductDto productDto) throws Exception {
        Optional<Product> updated = productRepository.findById(id);
        if(updated.isEmpty()){
            throw new Exception("Product not found");
        }
        Product product = updated.get();
        product.setDescription(productDto.getDescription());
        product.setImageUrl(productDto.getImageUrl());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        productRepository.save(product);
    }

    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
