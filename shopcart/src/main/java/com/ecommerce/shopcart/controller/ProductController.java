package com.ecommerce.shopcart.controller;

import com.ecommerce.shopcart.dto.ProductDto;
import com.ecommerce.shopcart.model.ApiResponse;
import com.ecommerce.shopcart.model.Category;
import com.ecommerce.shopcart.model.Product;
import com.ecommerce.shopcart.service.CategoryService;
import com.ecommerce.shopcart.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add-product")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto product) throws Exception {
        Optional<Category> category = categoryService.getById(product.getCategoryId());
        if (!categoryService.findById(product.getCategoryId())){
            return new ResponseEntity<>(new ApiResponse(false,"Category not found"),HttpStatus.NOT_FOUND);
        }
        productService.create(product,category.get());
        return new ResponseEntity<>(new ApiResponse(true,"Product created"),HttpStatus.CREATED);
    }

    @GetMapping("/products")
    public List<Product> listProducts(){
        return productService.list();
    }

    @GetMapping("/{id}")
    public Optional<Product> listById(@PathVariable Integer id){
        return productService.listById(id);
    }



}
