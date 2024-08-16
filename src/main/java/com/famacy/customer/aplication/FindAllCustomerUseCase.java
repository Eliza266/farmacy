package com.famacy.customer.aplication;

import java.util.List;

import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

public class FindAllCustomerUseCase {
    private final CustomerService customerService;

    public FindAllCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public List<Customer> execute(){
        return customerService.findAllCustomer();
    }

}
