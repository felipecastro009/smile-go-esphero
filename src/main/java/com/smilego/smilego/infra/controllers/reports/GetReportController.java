package com.smilego.smilego.infra.controllers.reports;

import com.smilego.smilego.application.usecases.reports.GetReportUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.ReportDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.ReportResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/reports")
public class GetReportController {
    private final GetReportUseCase getReportUseCase;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse handle() {
        return ReportDTOMapper.toResponse(getReportUseCase.execute());
    }
}
