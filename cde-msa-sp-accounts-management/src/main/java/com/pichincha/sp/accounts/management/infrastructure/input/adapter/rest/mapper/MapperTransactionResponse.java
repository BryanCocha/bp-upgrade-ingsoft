package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Account;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperTransactionResponse.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperTransactionResponse {
    Transaction toCreateTransactionResponse(com.pichincha.sp.accounts.management.domain.Transaction transaction);
    Transaction toUpgradeTransactionResponse(com.pichincha.sp.accounts.management.domain.Transaction transaction);
}
