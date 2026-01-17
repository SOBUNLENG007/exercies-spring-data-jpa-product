package org.example.exercisedatajpaproduct.dto.dtoResponse.customerResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.exercisedatajpaproduct.entity.Customer;
import org.example.exercisedatajpaproduct.entity.Email;
import org.example.exercisedatajpaproduct.entity.Order;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoGetCustomerResponseThree {

    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Email email;
    List<Order> orderList = new ArrayList<>();
    public void responseCustomerThree(Customer customer){
        id = customer.getId();
        name = customer.getName();
        address = customer.getAddress();
        phoneNumber = customer.getPhoneNumber();
        email = customer.getEmail();
    }
}
