package org.example.exercisedatajpaproduct.dto.dtoRequest.productOrderRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductOrderRequest {
    private Long productId;
    private Integer quantity;
}

