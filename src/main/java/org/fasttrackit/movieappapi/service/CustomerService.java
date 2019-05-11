package org.fasttrackit.movieappapi.service;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.fasttrackit.movieappapi.domain.Customer;
import org.fasttrackit.movieappapi.exception.ResourceNotFoundException;
import org.fasttrackit.movieappapi.persistence.CustomerRepository;
import org.fasttrackit.movieappapi.transfer.customer.CreateCustomerRequest;
import org.fasttrackit.movieappapi.transfer.customer.UpdateCustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private static final Logger LOGGER = (Logger) LoggerFactory.getLogger(Customer.class);

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, ObjectMapper objectMapper) {
        this.customerRepository = customerRepository;
        this.objectMapper = objectMapper;
    }

    public Customer createCustomer(CreateCustomerRequest request) {
        LOGGER.info("Creating product {}", request );
        Customer customer = objectMapper.convertValue(request, Customer.class);

        return customerRepository.save(customer);
    }

    public Customer getCustomer(long id) throws Exception {
        LOGGER.info("Retriving product {}", id );
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product" + id + "Resource not found"));
    }

    public Customer updateCustomer(long id, UpdateCustomerRequest request) throws Exception {
        LOGGER.info("Updating customer {}, {}", id, request);
        Customer customer = getCustomer(id);
        BeanUtils.copyProperties(request, customer);

        return customerRepository.save(customer);
    }




}
