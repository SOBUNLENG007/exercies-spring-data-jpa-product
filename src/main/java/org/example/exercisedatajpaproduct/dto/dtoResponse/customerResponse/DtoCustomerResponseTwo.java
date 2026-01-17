package org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoOrderResponse;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerResponseTwo {
    private DtoCustomerResponse customer;
    private List<DtoOrderResponse> orderList;

    public void responseCustomerWithList(Customer customer){
        this.customer = new DtoCustomerResponse();
        this.customer.responseCustomer(customer);

        this.orderList = new ArrayList<>();
        for (Order order : customer.getOrderList()) {
            DtoOrderResponse dtoOrderResponse = new DtoOrderResponse();
            dtoOrderResponse.responseOrder(order);
            this.orderList.add(dtoOrderResponse);
        }
    }
}
