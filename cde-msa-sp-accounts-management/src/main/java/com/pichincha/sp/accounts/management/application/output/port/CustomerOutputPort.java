package com.pichincha.sp.accounts.management.application.output.port;

import com.pichincha.sp.accounts.management.domain.Customer;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name CustomerOutputPort.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
public interface CustomerOutputPort {
    List<Customer> getCustomerById(String identification);
    Customer createCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(String identification);
    Customer editCustomer(Customer customer);
}
