package com.pichincha.sp.accounts.management.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author bryancocha@gmail.com
 * @class_name Cliente.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Getter
@Setter
@SuperBuilder
public class Customer extends Person {
    private String mgmtCustomerId;
    private String customerId;

    @NotNull
    @Pattern(regexp = "^.{3,24}$", message = "Debe tener entre 3 y 24 caracteres")
    private String password;

    private Boolean state;
}
