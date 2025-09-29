package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.domain.Account;
import org.mapstruct.Mapper;

/**
 * @author bryancocha@gmail.com
 * @class_name MapperAccountInput.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Mapper(componentModel = "spring")
public interface MapperAccountInput {
    Account toCreateAccountInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Account account);
    Account toUpgradeAccountInput(com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Account account);
}
