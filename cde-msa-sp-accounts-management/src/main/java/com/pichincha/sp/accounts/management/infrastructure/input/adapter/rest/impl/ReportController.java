package com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.impl;

import com.pichincha.sp.accounts.management.application.input.port.ReportInputPort;
import com.pichincha.sp.accounts.management.application.input.port.ReportPdfInputPort;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.ReportsApi;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.mapper.MapperReportResponse;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Report;
import com.pichincha.sp.accounts.management.infrastructure.input.adapter.rest.models.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportController.java
 * @class_description
 * @create_date 28/09/2025 Copyright 2022 Banco Pichincha.
 */
@Slf4j
@RestController
@RequiredArgsConstructor
public class ReportController implements ReportsApi {

    private final ReportInputPort reportInputPort;
    private final MapperReportResponse mapperReportResponse;

    @Override
    public ResponseEntity<List<Report>> getTransactionReport(String identification, String transactionDate) {
        var getReport = reportInputPort.getReportById(identification, transactionDate);
        List<Report> reportResponses = getReport.stream()
                .map(mapperReportResponse::toCreateReportResponse)
                .collect(java.util.stream.Collectors.toList());
        return new ResponseEntity<>(reportResponses, HttpStatus.OK);
    }

    private final ReportPdfInputPort reportPdfInputPort;

    @Override
    public ResponseEntity<org.springframework.core.io.Resource> getTransactionReportPdf(String identification, String transactionDate) {
        byte[] pdfBytes = reportPdfInputPort.generateReportPdf(identification, transactionDate);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(resource);
    }
}
