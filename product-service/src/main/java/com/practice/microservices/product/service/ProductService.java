package com.practice.microservices.product.service;

import com.practice.microservices.product.dto.ProductRequest;
import com.practice.microservices.product.dto.ProductResponse;
import com.practice.microservices.product.mapper.ProductMapper;
import com.practice.microservices.product.model.Product;
import com.practice.microservices.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductResponse createProduct(ProductRequest productRequest) {
        Product product = productMapper.mapToProduct(productRequest);
        productRepository.save(product);
        log.info("Product created successfully");
        return productMapper.mapToProductResponse(product);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::mapToProductResponse)
                .toList();
    }
}
