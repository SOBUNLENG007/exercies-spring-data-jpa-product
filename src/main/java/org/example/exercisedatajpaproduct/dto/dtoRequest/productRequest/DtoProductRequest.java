package org.example.exercisedatajpaproduct.dto.dtoRequest.productRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProductRequest {
    private String productName;
    private Double unitPrice;
    private String description;
    public void productRequest(Product product){
        product.setName(productName);
        product.setUnitPrice(unitPrice);
        product.setDescription(description);
    }
}
