package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.impl;

import com.pichincha.sp.accounts.management.application.input.port.CustomerInputPort;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.CustomersApi;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperCustomerInput;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperCustomerResponse;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Customer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author bryancocha@gmail.com
 * @class_name CustomersController.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class CustomersController implements CustomersApi {
    private final CustomerInputPort customerInputPort;
    private final MapperCustomerInput mapperCustomerInput;
    private final MapperCustomerResponse mapperCustomerResponse;

    @Override
    public ResponseEntity<Customer> createCustomer(Customer customer){
        com.pichincha.sp.accounts.management.domain.Customer customerRequest = mapperCustomerInput.toCreateCustomerInput(customer);
        var createdCustomer = customerInputPort.createCustomer(customerRequest);
        Customer customerResponse = mapperCustomerResponse.toCreateCustomerResponse(createdCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCustomerById(String identification) {
        customerInputPort.deleteCustomer(identification);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<List<Customer>> getCustomerById(String identification) {
        var getCustomer= customerInputPort.getCustomerById(identification);
        List<Customer> customerResponses = getCustomer.stream()
                .map(mapperCustomerResponse::toCreateCustomerResponse)
                .collect(Collectors.toList());
        return new ResponseEntity<>(customerResponses, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> patchCustomerById(Customer customer) {
        var customerRequest = mapperCustomerInput.toUpgradeCustomerInput(customer);
        var upgradedCustomer = customerInputPort.editCustomer(customerRequest);
        Customer customerResponse = mapperCustomerResponse.toUpgradeCustomerResponse(upgradedCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Customer> updateCustomerById(Customer customer) {
        var customerRequest = mapperCustomerInput.toUpgradeCustomerInput(customer);
        var upgradedCustomer = customerInputPort.updateCustomer(customerRequest);
        Customer customerResponse = mapperCustomerResponse.toUpgradeCustomerResponse(upgradedCustomer);
        return new ResponseEntity<>(customerResponse, HttpStatus.OK);
    }
}
