package com.ecommerce.shopcart.service;

import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.repository.CategoryRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public void updateCategory(Integer id , Category category) throws Exception {
        Category updated = repository.findById(id).orElseThrow(()-> new Exception("Id not found"));
        updated.setCategoryName(category.getCategoryName());
        updated.setImageUrl(category.getImageUrl());
        updated.setDescription(category.getDescription());
        repository.save(updated);
    }


    public Optional<Category> getById(Integer id) {
        return repository.findById(id);
    }

    public boolean findById(Integer id) {
        return repository.findById(id).isPresent();
    }

    public void deleteCategory(Integer id) {
        repository.deleteById(id);
    }
}
