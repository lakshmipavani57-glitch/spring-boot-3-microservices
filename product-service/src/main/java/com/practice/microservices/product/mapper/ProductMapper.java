package com.practice.microservices.product.mapper;

import com.practice.microservices.product.dto.ProductRequest;
import com.practice.microservices.product.dto.ProductResponse;
import com.practice.microservices.product.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface ProductMapper {

    @Mapping(target = "id", source = "productRequest.id")
    @Mapping(target = "name", source = "productRequest.name")
    @Mapping(target = "description", source = "productRequest.description")
    @Mapping(target = "price", source = "productRequest.price")
    Product mapToProduct(ProductRequest productRequest);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "price", target = "price")
    ProductResponse mapToProductResponse(Product product); //fpr setting record data,
    // we can directly give source param names, no need to use class name
}
