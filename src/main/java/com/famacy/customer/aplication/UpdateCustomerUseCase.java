package com.famacy.customer.aplication;

import com.famacy.customer.domain.Customer;
import com.famacy.customer.domain.CustomerService;

public class UpdateCustomerUseCase {
    private final CustomerService customerService;

    public UpdateCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(Customer customer) {
        customerService.updateCustomer(customer);
    }

}
