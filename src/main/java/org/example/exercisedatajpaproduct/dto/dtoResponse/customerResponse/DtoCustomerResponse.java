package org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.entity.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerResponse {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    public void responseCustomer(Customer customer)
    {
        id = customer.getId();
        name = customer.getName();
        address = customer.getAddress();
        phoneNumber = customer.getPhoneNumber();
        email = customer.getEmail();
    }
}
