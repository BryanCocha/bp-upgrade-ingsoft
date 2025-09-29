package com.pichincha.sp.accounts.management.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

/**
 * @author bryancocha@gmail.com
 * @class_name Persona.java
 * @class_description
 * @create_date 26/09/2025 Copyright 2022.
 */
@Getter
@Setter
@SuperBuilder
public class Person {
    private String id;

    private String name;

    private String gender;

    private Integer age;

    @NotNull
    @Pattern(regexp = "^.{3,24}$", message = "Debe tener entre 3 y 24 caracteres")
    private String identification;

    private String address;

    @Size(max = 20, message = "El número teléfonico debe tener máximo 20 dígitos numéricos")
    @Pattern(regexp = "^[0-9\\+\\-]*$", message = "El número teléfonico inválido")
    private String phone;
}
