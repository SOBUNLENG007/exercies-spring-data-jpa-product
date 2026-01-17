package org.example.exercisedatajpaproduct.controller;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.apiResponse.ApiResponse;
import org.example.exercisedatajpaproduct.dto.dtoRequest.customerRequest.DtoCustomerRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponseTwo;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoGetCustomerResponseThree;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/customer")
@AllArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @GetMapping("/getAllCustomer")
    public ResponseEntity<ApiResponse<Object>> getAllCustomer(
            @RequestParam(required = false, defaultValue = "1") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "ASC") String direction){
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page -1, size, sort);
        Page<DtoCustomerResponseTwo> customerPage = customerService.getAllCustomer(pageable);

        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get All customer successfully!")
                .payload(customerPage.getContent())
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);

    }

    @PostMapping("/saveCustomer")
    public ResponseEntity<ApiResponse<Object>> saveCustomer(@RequestBody DtoCustomerRequest dtoCustomerRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.saveCustomer(dtoCustomerRequest).getBody());
    }

    @GetMapping("/getCustomerById/{id}")
    public ResponseEntity<ApiResponse<Object>> getCustomerById(@PathVariable Long id){
        DtoCustomerResponse findCustomerById = customerService.getCustomerById(id);
        if (findCustomerById == null){
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Customer with id " + id +" not found!")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Get customer id "+ id +" successfully.")
                .payload(findCustomerById)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/deleteCustomerById/{id}")
    public ResponseEntity<ApiResponse<Object>> deleteCustomerById(@PathVariable Long id){
        Customer deleteCustomer = customerService.deleteCustomerById(id);
        if (deleteCustomer == null){
            ApiResponse<Object> apiResponse = ApiResponse.builder()
                    .message("Customer with id " + id +" not found!")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("Customer with id "+ id + " is deleted successfully!")
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }



    @PutMapping("/updateCustomerById/{id}")
    public ResponseEntity<ApiResponse<DtoGetCustomerResponseThree>> updateCustomerById(
            @PathVariable Long id,
            @RequestBody DtoCustomerRequest dtoCustomerRequest) {
        DtoGetCustomerResponseThree updateCustomerDto = customerService.updateCustomerById(dtoCustomerRequest, id);
        if (updateCustomerDto == null) {
            ApiResponse<DtoGetCustomerResponseThree> apiResponse = ApiResponse.<DtoGetCustomerResponseThree>builder()
                    .message("Customer with id " + id + " not found.")
                    .status(HttpStatus.NOT_FOUND)
                    .build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
        }
        ApiResponse<DtoGetCustomerResponseThree> apiResponse =  ApiResponse.<DtoGetCustomerResponseThree>builder()
                .message("Customer with Id " + id + " is updated successfully.")
                .payload(updateCustomerDto)
                .status(HttpStatus.OK)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
}
