package com.practice.micorservices.service;

import com.practice.micorservices.client.InventoryClient;
import com.practice.micorservices.dto.OrderRequest;
import com.practice.micorservices.mapper.OrderMapper;
import com.practice.micorservices.model.Order;
import com.practice.micorservices.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor //The constructor will be generated only for final fields whereas @AllArgsConstructor generates constructor for all fields
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest) {

        //Recommended to use BooleanUtils.isFalse() instead of '!'
        if (BooleanUtils.isFalse(inventoryClient.isInStock(orderRequest.skuCode(), orderRequest.quantity()))) {
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }
        Order order = orderMapper.mapToOrder(orderRequest);
        orderRepository.save(order);
        log.info("Order {} is saved successfully", order.getId());
    }
}
