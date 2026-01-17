package org.example.exercisedatajpaproduct.service.serviceImp;

import lombok.AllArgsConstructor;
import org.example.exercisedatajpaproduct.apiResponse.ApiResponse;
import org.example.exercisedatajpaproduct.dto.dtoRequest.customerRequest.DtoCustomerRequest;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponse;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoCustomerResponseTwo;
import org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse.DtoGetCustomerResponseThree;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.repository.CustomerRepository;
import org.example.exercisedatajpaproduct.service.CustomerService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImp implements CustomerService {
    private final CustomerRepository customerRepository;

    @Override
    public ResponseEntity<ApiResponse<Object>> saveCustomer(DtoCustomerRequest dtoCustomerRequest) {
        Customer customer = new Customer();
        dtoCustomerRequest.requestCustomer(customer);
        Customer savedCustomer = customerRepository.save(customer);
        DtoCustomerResponse dtoCustomerResponse = new DtoCustomerResponse();
        dtoCustomerResponse.responseCustomer(savedCustomer);
        ApiResponse<Object> apiResponse = ApiResponse.builder()
                .message("A new customer is inserted successfully!")
                .payload(dtoCustomerResponse)
                .status(HttpStatus.CREATED)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

    @Override
    public Page<DtoCustomerResponseTwo> getAllCustomer(Pageable pageable) {
        Page<Customer> customerPage = customerRepository.findAll(pageable);

        List<DtoCustomerResponseTwo> dtoCustomerResponseList = new ArrayList<>();
        for(Customer customer : customerPage){
            DtoCustomerResponseTwo dtoCustomerResponseTow = new DtoCustomerResponseTwo();
            dtoCustomerResponseTow.responseCustomerWithList(customer);
            dtoCustomerResponseList.add(dtoCustomerResponseTow);
        }
        return new PageImpl<>(dtoCustomerResponseList,pageable,customerPage.getTotalElements());
    }

    @Override
    public DtoCustomerResponse getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id).orElse(null);
        DtoCustomerResponse dtoCustomerResponse = new DtoCustomerResponse();
        dtoCustomerResponse.responseCustomer(customer);
        return dtoCustomerResponse;
    }

    @Override
    public DtoGetCustomerResponseThree updateCustomerById(DtoCustomerRequest dtoCustomerRequest, Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customer1.setName(dtoCustomerRequest.getCustomerName());
            customer1.setAddress(dtoCustomerRequest.getAddress());
            customer1.setPhoneNumber(dtoCustomerRequest.getPhoneNumber());
            if (customer1.getEmail() != null){
                customer1.getEmail().setEmail(dtoCustomerRequest.getEmail());
            }
            customerRepository.save(customer1);
            DtoGetCustomerResponseThree dtoCustomerResponse = new DtoGetCustomerResponseThree();
            dtoCustomerResponse.responseCustomerThree(customer1);
            return dtoCustomerResponse;
        }else {
            return null;
        }
    }

    @Override
    public Customer deleteCustomerById(Long id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()) {
            Customer customer1 = customer.get();
            customerRepository.delete(customer1);
            return  customer1;
        }
        return null;
    }
}
