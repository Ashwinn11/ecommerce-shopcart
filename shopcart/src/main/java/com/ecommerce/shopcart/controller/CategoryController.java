package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService service;

    @GetMapping("/")
    public List<Category> getCategories(){
        return service.getAll();
    }
    @PostMapping("/create-category")
    public String create(@RequestBody Category category){
        service.createCategory(category);
        return "Success";
    }
}
