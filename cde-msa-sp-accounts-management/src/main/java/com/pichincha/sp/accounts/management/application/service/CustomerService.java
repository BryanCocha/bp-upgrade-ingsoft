package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.input.port.CustomerInputPort;
import com.pichincha.sp.accounts.management.application.output.port.CustomerOutputPort;
import com.pichincha.sp.accounts.management.domain.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name CustomerService.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService implements CustomerInputPort {

    private final CustomerOutputPort customerOutputPort;

    @Override
    public List<Customer> getCustomerById(String identification) {
        return customerOutputPort.getCustomerById(identification);
    }

    @Override
    public Customer createCustomer(Customer customer) {
        return customerOutputPort.createCustomer(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerOutputPort.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(String identification) {
        customerOutputPort.deleteCustomer(identification);
    }

    @Override
    public Customer editCustomer(Customer customer) {
        return customerOutputPort.editCustomer(customer);
    }
}
