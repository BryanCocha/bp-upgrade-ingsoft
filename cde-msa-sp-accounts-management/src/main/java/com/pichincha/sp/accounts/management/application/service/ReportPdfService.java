package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.input.port.ReportPdfInputPort;
import com.pichincha.sp.accounts.management.application.output.port.ReportPdfOutputPort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportPdfService.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ReportPdfService implements ReportPdfInputPort {
    private final ReportPdfOutputPort reportPdfOutputPort;
    @Override
    public byte[] generateReportPdf(String identification, String transactionDate) {
        return reportPdfOutputPort.generateReportPdf(identification, transactionDate);
    }
}
