package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.domain.Account;
import com.pichincha.sp.accounts.management.domain.Transaction;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperTransactionInput.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperTransactionInput {
    Transaction toCreateTransactionInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction transaction);
    Transaction toUpgradeTransactionInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction transaction);
}
