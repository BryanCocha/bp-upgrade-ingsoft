package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.application.output.port.ReportPdfOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportPdfServiceTest {
    @Mock
    private ReportPdfOutputPort reportPdfOutputPort;

    @InjectMocks
    private ReportPdfService reportPdfService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGenerateReportPdf() {
        String identification = "1234567890";
        String transactionDate = "2025-09-29";
        byte[] expectedPdf = new byte[]{1, 2, 3};
        when(reportPdfOutputPort.generateReportPdf(identification, transactionDate)).thenReturn(expectedPdf);
        byte[] result = reportPdfService.generateReportPdf(identification, transactionDate);
        assertArrayEquals(expectedPdf, result);
        verify(reportPdfOutputPort).generateReportPdf(identification, transactionDate);
    }
}

