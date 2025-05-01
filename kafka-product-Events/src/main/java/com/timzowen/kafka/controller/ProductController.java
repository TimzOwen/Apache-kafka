package com.timzowen.kafka.controller;

import com.timzowen.kafka.exceptions.ErrorMessage;
import com.timzowen.kafka.model.Product;
import com.timzowen.kafka.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Objects;

@RestController
@RequestMapping("/api/v1/products/")
public class ProductController {

    private final ProductService productService;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("create-product")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        String productId = null;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {
            LOGGER.error(e.getMessage(),e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorMessage(new Date(),e.getMessage(),"/create-product"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }

}
