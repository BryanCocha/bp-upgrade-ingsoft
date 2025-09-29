package com.pichincha.sp.accounts.management.infrastructure.output.adapter;

import com.pichincha.sp.accounts.management.application.output.port.ReportPdfOutputPort;
import com.pichincha.sp.accounts.management.application.output.port.ReportOutputPort;
import com.pichincha.sp.accounts.management.domain.Report;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.stereotype.Repository;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * @author bcochaba@pichincha.com
 * @class_name ReportPdfOutputAdapter.java
 * @class_description
 * @create_date 29/09/2025 Copyright 2022 Banco Pichincha.
 */
@Repository
@RequiredArgsConstructor
public class ReportPdfOutputAdapter implements ReportPdfOutputPort {
    private final ReportOutputPort reportOutputPort;

    @Override
    public byte[] generateReportPdf(String identification, String transactionDate) {
        List<Report> reportList = reportOutputPort.getReportById(identification, transactionDate);
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage(PDRectangle.LETTER);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
            contentStream.beginText();
            contentStream.newLineAtOffset(50, 700);
            contentStream.showText("Reporte de Movimientos");
            contentStream.newLineAtOffset(0, -20);
            contentStream.setFont(PDType1Font.HELVETICA, 10);
            for (Report r : reportList) {
                String linea = String.format("Fecha: %s | Cliente: %s | Cuenta: %s | Tipo: %s | Saldo: %s | Estado: %s | Monto: %s", r.getTransactionDate(), r.getName(), r.getAccountNumber(), r.getTransactionType(), r.getBalance(), r.getState(), r.getAmount());
                contentStream.showText(linea);
                contentStream.newLineAtOffset(0, -15);
            }
            contentStream.endText();
            contentStream.close();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            document.save(baos);
            return baos.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Error generando PDF", e);
        }
    }
}
