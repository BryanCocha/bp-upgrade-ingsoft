package com.pichincha.sp.accounts.management.infrastructure.exception;

import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.ErrorModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author bcochaba@pichincha.com
 * @class_name GlobalExceptionHandler.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public GlobalExceptionHandler() {
        // This constructor is intentionally empty.
        // It is provided for potential future use or for framework requirements.
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<?> handleNullPointerException(NullPointerException e, WebRequest req) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDetail("NullPointerException: " + e.getMessage());
        errorModel.setInstance(req.getDescription(false));
        errorModel.setType("/errors/null-pointer");
        errorModel.component("cde-msa-sp-accounts-management");
        errorModel.setBackend("00647");
        return new ResponseEntity<>(errorModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(Throwable.class)
    public ResponseEntity<?> handleGeneralException(Exception e, WebRequest req) {

        ErrorModel errorModel = new ErrorModel();
        errorModel.setDetail(e.getMessage());
        errorModel.setInstance(req.getDescription(false));
        errorModel.setType("/errors/internal-server-error");
        errorModel.component("cde-msa-sp-accounts-management");
        errorModel.setBackend("00647");

        return new ResponseEntity<>(errorModel, HttpStatus.SERVICE_UNAVAILABLE);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorModel> handleNotFoundException(NotFoundException e, WebRequest req) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDetail("Recurso no encontrado: " + e.getMessage());
        errorModel.setInstance(req.getDescription(false));
        errorModel.setType("/errors/not-found");
        errorModel.component("cde-msa-sp-accounts-management");
        errorModel.setBackend("00647");
        return new ResponseEntity<>(errorModel, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ValidatorBeanException.class)
    public ResponseEntity<ErrorModel> handleValidatorBeanException(ValidatorBeanException e, WebRequest req) {
        ErrorModel errorModel = new ErrorModel();
        errorModel.setDetail("Error de validación: " + e.getErrors().get(0));
        errorModel.setInstance(req.getDescription(false));
        errorModel.setType("/errors/validation-error");
        errorModel.component("cde-msa-sp-accounts-management");
        errorModel.setBackend("00647");
        return new ResponseEntity<>(errorModel, HttpStatus.BAD_REQUEST);
    }
}
