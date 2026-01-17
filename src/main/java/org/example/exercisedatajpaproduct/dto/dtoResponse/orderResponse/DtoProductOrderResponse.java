package org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProductOrderResponse {
    private Long id;
    private String name;
    private int quantity;
}
