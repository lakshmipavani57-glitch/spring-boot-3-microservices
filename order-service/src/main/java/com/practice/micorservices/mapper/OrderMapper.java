package com.practice.micorservices.mapper;

import com.practice.micorservices.dto.OrderRequest;
import com.practice.micorservices.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.ERROR, componentModel = "spring")
public interface OrderMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "orderNumber", expression = "java(java.util.UUID.randomUUID().toString())")
    @Mapping(target = "skuCode", source = "skuCode")
    @Mapping(target = "price", source = "price")
    @Mapping(target = "quantity", source = "quantity")
    Order mapToOrder(OrderRequest order);
}
