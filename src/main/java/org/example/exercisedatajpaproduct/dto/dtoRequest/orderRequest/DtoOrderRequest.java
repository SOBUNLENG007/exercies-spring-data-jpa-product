package org.example.exercisedatajpaproduct.dto.dtoRequest.orderRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.ProductOrder;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoOrderRequest {
    private Integer quantity;
    private Long productId;
    public void requestOrder(ProductOrder productOrder){
        productOrder.setId(productId);
    }
}

