package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.domain.Customer;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperCustomersInput.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperCustomerInput {
    default Customer toCreateCustomerInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Customer customer) {
        return Customer.builder()
            .name(customer.getName())
            .gender(customer.getGender())
            .age(customer.getAge())
            .identification(customer.getIdentification())
            .address(customer.getAddress())
            .phone(customer.getPhone())
            .customerId(customer.getCustomerId())
            .password(customer.getPassword())
            .state(customer.getState())
            .build();
    }
    Customer toUpgradeCustomerInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Customer customer);
}
