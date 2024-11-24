package com.smilego.smilego.infra.controllers.dtos.mappers;

import com.smilego.smilego.domain.Report;
import com.smilego.smilego.infra.controllers.dtos.responses.ReportResponse;

import java.math.BigDecimal;

public class ReportDTOMapper {
    public static ReportResponse toResponse(Report report) {
        return new ReportResponse(report.getActiveSubscriptions(), report.getInactiveSubscriptions(), report.getTotalAmount());
    }
}
