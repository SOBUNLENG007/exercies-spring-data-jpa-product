package org.example.exercisedatajpaproduct.service;

import org.example.exercisedatajpaproduct.dto.dtoRequest.productOrderRequest.ProductOrderRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoGetOrderResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoOrderResponse;
import org.example.exercisedatajpaproduct.dto.enums.Status;

import java.util.List;

public interface OrderService {
    DtoOrderResponse saveOrder(Long customerId, List<ProductOrderRequest> productOrderRequests);

    DtoGetOrderResponse getOrderById(Long orderId);

    DtoGetOrderResponse updateOrder(Status status, Long orderId);

    DtoGetOrderResponse getOrderByCustomerId(Long customerId);


}
