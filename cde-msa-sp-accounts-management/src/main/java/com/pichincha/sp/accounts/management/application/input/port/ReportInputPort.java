package com.pichincha.sp.accounts.management.application.input.port;

import com.pichincha.sp.accounts.management.domain.Customer;
import com.pichincha.sp.accounts.management.domain.Report;

import java.util.List;

/**
 * @author bryancocha@gmail.com
 * @class_name ReportInputPort.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022.
 */
public interface ReportInputPort {
    List<Report> getReportById(String identification, String transactionDate);
}
