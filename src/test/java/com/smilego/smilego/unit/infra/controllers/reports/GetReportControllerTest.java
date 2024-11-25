package com.smilego.smilego.unit.infra.controllers.reports;

import com.smilego.smilego.application.usecases.reports.GetReportUseCase;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.infra.controllers.dtos.mappers.ReportDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.ReportResponse;
import com.smilego.smilego.infra.controllers.reports.GetReportController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GetReportControllerTest {

    @Mock
    private GetReportUseCase getReportUseCase;

    @InjectMocks
    private GetReportController getReportController;

    @Test
    void shouldReturnReportSuccessfullyWhenDatesAreProvided() {
        LocalDate startDate = LocalDate.of(2023, 11, 1);
        LocalDate endDate = LocalDate.of(2023, 11, 30);
        Report report = new Report(
                1, 1, BigDecimal.ZERO
        );
        when(getReportUseCase.execute(startDate, endDate)).thenReturn(report);
        ReportResponse expectedResponse = ReportDTOMapper.toResponse(report);
        ReportResponse actualResponse = getReportController.handle(startDate, endDate);
        assertEquals(expectedResponse, actualResponse);
        verify(getReportUseCase, times(1)).execute(startDate, endDate);
    }

    @Test
    void shouldReturnReportSuccessfullyWhenDatesAreNull() {
        Report report = new Report(
                1, 1, BigDecimal.ZERO
        );
        when(getReportUseCase.execute(null, null)).thenReturn(report);
        ReportResponse expectedResponse = ReportDTOMapper.toResponse(report);
        ReportResponse actualResponse = getReportController.handle(null, null);
        assertEquals(expectedResponse, actualResponse);
        verify(getReportUseCase, times(1)).execute(null, null);
    }
}
