package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper;

import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Report;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction;
import org.mapstruct.Mapper;

/**
 * @author bcochaba@pichincha.com
 * @class_name MapperReportResponse.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Mapper(componentModel = "spring")
public interface MapperReportResponse {
    Report toCreateReportResponse(com.pichincha.sp.accounts.management.domain.Report report);
}
