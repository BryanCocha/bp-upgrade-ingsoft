package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Account;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperResponseAccount.java
 * @class_description 
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperAccountResponse {
    Account toCreateAccountResponse(com.pichincha.sp.accounts.management.domain.Account account);
    Account toUpgradeAccountResponse(com.pichincha.sp.accounts.management.domain.Account account);
}
