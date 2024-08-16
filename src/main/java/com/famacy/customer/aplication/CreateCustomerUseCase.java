package com.famacy.customer.aplication;

import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

public class CreateCustomerUseCase {
    private final CustomerService customerService;

    public CreateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer){
        customerService.createCustomer(customer);
    }

}
