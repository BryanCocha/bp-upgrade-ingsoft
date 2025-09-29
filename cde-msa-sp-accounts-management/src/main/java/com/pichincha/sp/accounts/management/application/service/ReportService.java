package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.input.port.ReportInputPort;
import com.pichincha.sp.accounts.management.application.output.port.ReportOutputPort;
import com.pichincha.sp.accounts.management.domain.Report;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportService.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022 Banco Pichincha.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportService implements ReportInputPort {
    private final ReportOutputPort reportOutputPort;
    @Override
    public List<Report> getReportById(String identification, String transactionDate) {
        return reportOutputPort.getReportById(identification, transactionDate);
    }
}
