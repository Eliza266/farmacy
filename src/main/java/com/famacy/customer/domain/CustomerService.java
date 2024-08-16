package com.famacy.customer.domain;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    void createCustomer(Customer customer);

    void updateCustomer(Customer customer);

    void deleteCustomer(String idCust);

    Optional<Customer> findCustomerById(String idCust);

    List<Customer> findAllCustomer();
}
