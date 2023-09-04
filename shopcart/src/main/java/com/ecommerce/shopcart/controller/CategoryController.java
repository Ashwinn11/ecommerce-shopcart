package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.model.ApiResponse;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/category")
    public List<Category> getCategories(){
        return service.getAll();
    }
    @GetMapping("/category/{id}")
    public Optional<Category> getCategory(@PathVariable Integer id){
        return service.getById(id);
    }
    @PostMapping("/create-category")
    public ResponseEntity<ApiResponse> create(@RequestBody Category category){
        service.createCategory(category);
        return new ResponseEntity<>(new ApiResponse(true,"Category created"),HttpStatus.CREATED);
    }
    @PutMapping("/create-category/{id}")
    public ResponseEntity<ApiResponse> update(@PathVariable Integer id, @RequestBody Category category )
            throws Exception {
        if(!service.findById(id)){
            return new ResponseEntity<>(new ApiResponse(false,"Id not found"),HttpStatus.NOT_FOUND);
        }
        service.updateCategory(id,category);
        return new ResponseEntity<>(new ApiResponse(true,"Updated successfully"),HttpStatus.ACCEPTED);
    }
}
