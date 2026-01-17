package org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.Product;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoProductResponse {
    private Long productId;
    private String productName;
    private Double unitPrice;
    private String description;
    public void productResponse(Product product){
        this.productId=product.getId();
        this.productName=product.getName();
        this.unitPrice=product.getUnitPrice();
        this.description=product.getDescription();
    }
}
