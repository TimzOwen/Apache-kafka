package com.timzowen.kafka.service.impl;

import com.timzowen.kafka.events.ProductCreatedEvent;
import com.timzowen.kafka.model.Product;
import com.timzowen.kafka.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    // Step 1 ---> Define the kafka template & Logger & do constructor injection
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate){
        this.kafkaTemplate = kafkaTemplate;
    }

    // Step 2 ---> Product event
    @Override
    public String createProduct(Product product) {
        String productId = UUID.randomUUID().toString(); // unique identifier for product
        // TODO ---> Persist data in db.
        ProductCreatedEvent productCreatedEvent =
                new ProductCreatedEvent(productId, product.getTitle(), product.getPrice(),product.getQuantity());

        CompletableFuture<SendResult<String, ProductCreatedEvent>> future =  kafkaTemplate.send("product-created-events-topic",productId,productCreatedEvent);
        // listen to completion
        future.whenComplete((result,exception) -> {
           if (exception != null){
               LOGGER.error("********* Failed to send message{}", exception.getMessage());
           }else {
               LOGGER.info("********* message sent successfully{}",result.getRecordMetadata());
           }
        });

        LOGGER.info("*********** Returning product id");
        return productId;
    }
}
