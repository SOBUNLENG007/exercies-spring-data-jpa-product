package org.example.exercisedatajpaproduct.controller;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.apiResponse.ApiResponse;
import org.example.exercisedatajpaproduct.dto.dtoRequest.productOrderRequest.ProductOrderRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoGetOrderResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoOrderResponse;
import org.example.exercisedatajpaproduct.dto.enums.Status;
import org.example.exercisedatajpaproduct.entity.Order;
import org.example.exercisedatajpaproduct.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {
    private OrderService orderService;

    @PostMapping("/saveOrder/{CustomerId}")
    public ResponseEntity<ApiResponse<Object>>  saveOrder(
            @PathVariable Long CustomerId,
            @RequestBody List<ProductOrderRequest> productOrderRequest){
        DtoOrderResponse orderResponse = orderService.saveOrder(CustomerId, productOrderRequest);
        ApiResponse<Object> response = ApiResponse.builder()
                .message("A new order is created successfully.")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getOrderById/{orderId}")
    public ResponseEntity<ApiResponse<Object>> getOrderById(@PathVariable Long orderId) {
        DtoGetOrderResponse orderResponse = orderService.getOrderById(orderId);
        if (orderResponse == null){
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Customer with id " + orderId +" not found!")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get order id " + orderId + " successfully.")
                .payload(orderResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @PutMapping("/updateOrder/status")
    public ResponseEntity<ApiResponse<DtoGetOrderResponse>> updateOrder(
            @RequestParam Status status,
            @RequestParam Long orderId
    ) {
        DtoGetOrderResponse updatedOrderDto = orderService.updateOrder(status, orderId);
        if (updatedOrderDto == null) {
            ApiResponse<DtoGetOrderResponse> apiResponse = ApiResponse.<DtoGetOrderResponse>builder()
                    .message("Order with ID " + orderId + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<DtoGetOrderResponse> apiResponse = ApiResponse.<DtoGetOrderResponse>builder()
                .message("Successfully updated the status of order to " + status)
                .payload(updatedOrderDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }


    @GetMapping("/getOrderByCustomerId/{customerId}")
    public ResponseEntity<ApiResponse<DtoGetOrderResponse>> getOrderByCustomerId(@PathVariable Long customerId) {
        DtoGetOrderResponse dtoGetOrderResponse = orderService.getOrderByCustomerId(customerId);
        if (dtoGetOrderResponse == null) {
            ApiResponse<DtoGetOrderResponse> apiResponse = ApiResponse.<DtoGetOrderResponse>builder()
                    .message("No orders found for customer with ID " + customerId)
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<DtoGetOrderResponse> apiResponse = ApiResponse.<DtoGetOrderResponse>builder()
                .message("Get all orders with customer id "+ customerId +" successfully. ")
                .payload(dtoGetOrderResponse)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
