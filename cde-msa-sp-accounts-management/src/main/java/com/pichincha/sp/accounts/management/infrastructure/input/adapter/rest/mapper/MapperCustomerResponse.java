package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Customer;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperCustomersResponse.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperCustomerResponse {
    Customer toCreateCustomerResponse(com.pichincha.sp.accounts.management.domain.Customer customer);
    Customer toUpgradeCustomerResponse(com.pichincha.sp.accounts.management.domain.Customer customer);
}
