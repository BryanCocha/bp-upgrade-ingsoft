package com.pichincha.sp.accounts.management.application.service;

import com.pichincha.sp.accounts.management.domain.Report;
import com.pichincha.sp.accounts.management.application.output.port.ReportOutputPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Collections;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReportServiceTest {
    @Mock
    private ReportOutputPort reportOutputPort;

    @InjectMocks
    private ReportService reportService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReportById() {
        Report report = Report.builder()
            .transactionDate(java.time.LocalDate.now())
            .name("Juan Perez")
            .accountNumber("1234567890")
            .transactionType("DEP")
            .balance(java.math.BigDecimal.TEN)
            .state(true)
            .amount(java.math.BigDecimal.TEN)
            .build();
        when(reportOutputPort.getReportById("1234567890", "2025-09-29"))
            .thenReturn(Collections.singletonList(report));
        List<Report> result = reportService.getReportById("1234567890", "2025-09-29");
        assertEquals(1, result.size());
        verify(reportOutputPort).getReportById("1234567890", "2025-09-29");
    }
}
