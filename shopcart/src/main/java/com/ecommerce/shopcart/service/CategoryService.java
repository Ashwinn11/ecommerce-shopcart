package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public void createCategory(Category category){
        repository.save(category);
    }

    public List<Category> getAll() {
        return repository.findAll();
    }
}
