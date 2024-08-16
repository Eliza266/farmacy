package com.famacy.customer.aplication;

import com.famacy.customer.domain.CustomerService;

public class DeleteCustomerUseCase {
    private final CustomerService customerService;

    public DeleteCustomerUseCase(CustomerService customerService) {
        this.customerService = customerService;
    }

    public void execute(String idCust) {
        customerService.deleteCustomer(idCust);
    }
}
