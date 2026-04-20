package com.practice.micorservices.dto;

import lombok.*;

import java.math.BigDecimal;

public record OrderRequest(Long id,
        String skuCode,
        BigDecimal price,
        Integer quantity
) {
}
