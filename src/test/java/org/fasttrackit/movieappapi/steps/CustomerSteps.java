package org.fasttrackit.movieappapi.steps;

import org.fasttrackit.movieappapi.domain.Customer;
import org.fasttrackit.movieappapi.service.CustomerService;
import org.fasttrackit.movieappapi.transfer.customer.CreateCustomerRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerSteps {

    @Autowired
    private CustomerService customerService;

    public Customer createCustomer() {
        CreateCustomerRequest customer = new CreateCustomerRequest();
        customer.setFirstName("Jhon");
        customer.setLastName("Doe");
        customer.setAddress("test address");
        customer.setEmail("Jhon@exemple.com");
        customer.setPhone("0423313");
        customer.setUsername("jhonny");
        customer.setPassword("pass");

        return customerService.createCustomer(customer);
    }
}
