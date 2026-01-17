package org.example.exercisedatajpaproduct.service;

import org.example.exercisedatajpaproduct.apiResponse.ApiResponse;
import org.example.exercisedatajpaproduct.dto.dtoRequest.customerRequest.DtoCustomerRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponseTwo;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoGetCustomerResponseThree;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface CustomerService {
    ResponseEntity<ApiResponse<Object>> saveCustomer(DtoCustomerRequest dtoCustomerRequest);

    Page<DtoCustomerResponseTwo> getAllCustomer(Pageable pageable);

    DtoCustomerResponse getCustomerById(Long id);

    DtoGetCustomerResponseThree updateCustomerById(DtoCustomerRequest dtoCustomerRequest, Long id);

    Customer deleteCustomerById(Long id);
}
