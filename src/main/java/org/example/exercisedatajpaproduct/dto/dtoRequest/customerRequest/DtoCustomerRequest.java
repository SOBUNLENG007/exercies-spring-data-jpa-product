package org.example.exercisedatajpaproduct.dto.dtoRequest.customerRequest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.entity.Email;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoCustomerRequest {
    private String customerName;
    private String address;
    private String phoneNumber;
    private String email;
    
    public void requestCustomer(Customer customer){
        customer.setName(customerName);
        customer.setAddress(address);
        customer.setPhoneNumber(phoneNumber);
        customer.setEmail(new Email(null, email));
    }

}
