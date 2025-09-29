package com.pichincha.sp.accounts.management.domain.util;

import com.pichincha.sp.accounts.management.infrastructure.exception.ValidatorBeanException;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.ErrorModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author bcochaba@pichincha.com
 * @class_name util.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
public class UtilClass {
    private UtilClass() {
    }

    public static <T> void validateBean(T instanceClassValidated) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<T>> constraintViolations = validator.validate(instanceClassValidated);

            if (!constraintViolations.isEmpty()) {
                List<String> errors = new ArrayList<>();
                for (ConstraintViolation<T> constraintViolation : constraintViolations) {
                    String errorMsg = String.format("field: %s value: '%s' message: %s",
                            constraintViolation.getPropertyPath(),
                            constraintViolation.getInvalidValue(),
                            constraintViolation.getMessage());
                    errors.add(errorMsg);
                }
                throw new ValidatorBeanException(Constants.EXCEPTION_BAD_REQUEST, errors);
            }
        }
    }

}
