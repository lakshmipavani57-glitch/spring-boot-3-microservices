package com.practice.microservices.product.controller;

import com.practice.microservices.product.dto.ProductRequest;
import com.practice.microservices.product.dto.ProductResponse;
import com.practice.microservices.product.model.Product;
import com.practice.microservices.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@Slf4j
@AllArgsConstructor
@Tag(name = "products", description = "Operations related to products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new product", description = "Creates a new product with the provided details")
    public ProductResponse createProduct(@RequestBody ProductRequest productRequest) {
        log.info("Creating a new product");
        return productService.createProduct(productRequest);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all products", description = "Retrieves a list of all products")
    public List<ProductResponse> getAllProducts() {
        log.info("Fetching all products");
        return productService.getAllProducts();
    }
}
