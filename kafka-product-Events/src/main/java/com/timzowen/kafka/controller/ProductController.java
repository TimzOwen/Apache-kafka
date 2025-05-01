package com.timzowen.kafka.controller;

import com.timzowen.kafka.model.Product;
import com.timzowen.kafka.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("create-product")
    public ResponseEntity<String> createProduct(@RequestBody Product product){
        String productId = productService.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
