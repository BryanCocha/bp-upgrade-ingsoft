package com.pichincha.sp.accounts.management.application.output.port;

import com.pichincha.sp.accounts.management.domain.Report;

import java.util.List;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportOutputPort.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022 Banco Pichincha.
 */
public interface ReportOutputPort {
    List<Report> getReportById(String identification, String transactionDate);
}
