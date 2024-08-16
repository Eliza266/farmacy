package com.famacy.customer.aplication;

import java.util.Optional;

import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

public class FindCustomerUseCase {
    private final CustomerService customerService;

    public FindCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public Optional<Customer> execute(String idCust) {
        return customerService.findCustomerById(idCust);
    }
}
