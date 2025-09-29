package com.pichincha.sp.accounts.management.application.output.port;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportPdfInputPort.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
public interface ReportPdfOutputPort {
    byte[] generateReportPdf(String identification, String transactionDate);
}
