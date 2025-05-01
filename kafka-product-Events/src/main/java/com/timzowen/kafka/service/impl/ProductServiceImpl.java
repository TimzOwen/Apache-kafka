package com.timzowen.kafka.service.impl;

import com.timzowen.kafka.model.Product;
import com.timzowen.kafka.service.ProductService;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public String createProduct(Product product) {
        return "";
    }
}
