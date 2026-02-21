package org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones.DtoProductResponse;
import org.example.exercisedatajpaproduct.entity.Order;
import org.example.exercisedatajpaproduct.entity.ProductOrder;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoGetOrderResponse {
    private Long id;
    private LocalDate orderDate;
    private float totalAmount;
    private String status;
    private List<DtoProductResponse> productList = new ArrayList<>();

     public void responseGetOrder(Order order){
         this.id =  order.getId();

         this.orderDate = order.getOrderDate();
         this.totalAmount = order.getTotalAmount();
         this.status = order.getStatus().toString();
         for(ProductOrder productOrder : order.getProductOrderList()){
             DtoProductResponse dtoProductResponse = new DtoProductResponse();
             dtoProductResponse.productResponse(productOrder.getProduct());
             this.productList.add(dtoProductResponse);
         }
     }
}
