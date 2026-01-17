package org.example.exercisedatajpaproduct.service.serviceImp;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.dto.dtoRequest.productOrderRequest.ProductOrderRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoGetOrderResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.orderResponse.DtoOrderResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.productRespones.DtoProductResponse;
import org.example.exercisedatajpaproduct.dto.enums.Status;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.entity.Order;
import org.example.exercisedatajpaproduct.entity.Product;
import org.example.exercisedatajpaproduct.entity.ProductOrder;
import org.example.exercisedatajpaproduct.repository.CustomerRepository;
import org.example.exercisedatajpaproduct.repository.OrderRepository;
import org.example.exercisedatajpaproduct.repository.ProductRepository;
import org.example.exercisedatajpaproduct.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImp implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public DtoOrderResponse saveOrder(Long customerId, List<ProductOrderRequest> productOrderRequest) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found."));
        Order order = new Order();
        order.setOrderDate(LocalDate.now());
        order.setCustomer(customer);
        order.setStatus(Status.PENDING);
        List<ProductOrder> productOrders = new ArrayList<>();
        // Calculate
        float totalAmount = 0;
        for(ProductOrderRequest request : productOrderRequest){
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found."));
            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setOrder(order);
            productOrder.setQuantity(request.getQuantity());
            productOrders.add(productOrder);

            totalAmount += (float) (product.getUnitPrice() *request.getQuantity());
        }
        order.setTotalAmount(totalAmount);
        order.setProductOrderList(productOrders);
        // Save the order

        Order savedOrder = orderRepository.save(order);
        // Convert the saved Order to DtoOrderResponse
        DtoOrderResponse dtoOrderResponse = new DtoOrderResponse();
        dtoOrderResponse.responseOrder(savedOrder);
        List<DtoProductResponse> productResponses = savedOrder.getProductOrderList().stream()
                .map(productOrder -> {
                    DtoProductResponse dtoProductResponse = new DtoProductResponse();
                    dtoProductResponse.productResponse(productOrder.getProduct());
                    return dtoProductResponse;
                }).collect(Collectors.toList());
        dtoOrderResponse.setProductList(productResponses);
        return dtoOrderResponse;
    }

    @Override
    public DtoGetOrderResponse getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        DtoGetOrderResponse orderResponse = new DtoGetOrderResponse();
        orderResponse.responseGetOrder(order);

        List<DtoProductResponse> productResponses = order.getProductOrderList().stream()
                .map(productOrder -> {
                    DtoProductResponse productResponse = new DtoProductResponse();
                    productResponse.productResponse(productOrder.getProduct());
                    return productResponse;
                }).collect(Collectors.toList());

        orderResponse.setProductList(productResponses);
        return orderResponse;
    }

    @Override
    public DtoGetOrderResponse updateOrder(Status status, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order != null) {
            order.setStatus(status);
            Order updatedOrder = orderRepository.save(order);
            // convert to dto
            DtoGetOrderResponse dtoGetOrderResponse = new DtoGetOrderResponse();
            dtoGetOrderResponse.responseGetOrder(updatedOrder);
            return dtoGetOrderResponse;
        }
        return null;
    }

    @Override
    public DtoGetOrderResponse getOrderByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);

        if (orders.isEmpty()) {
            return null;
        }
        // Convert to DTOGetOrderResponse
        Order order = orders.get(0);
        DtoGetOrderResponse dtoGetOrderResponse = new DtoGetOrderResponse();
        dtoGetOrderResponse.responseGetOrder(order);
        return dtoGetOrderResponse;
    }
}
