package com.smilego.smilego.infra.controllers.reports;

import com.smilego.smilego.application.usecases.reports.GetReportUseCase;
import com.smilego.smilego.infra.controllers.dtos.mappers.ReportDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.ReportResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@AllArgsConstructor
@RestController
@RequestMapping("/reports")
@Tag(name = "Relatórios", description = "Relatórios da aplicação")
public class GetReportController {
    private final GetReportUseCase getReportUseCase;

    @Operation(summary = "Retorna o relatório da aplicação", description = "Retorna o relatório da aplicação")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ReportResponse handle(
            @RequestParam(value = "startDate", required = false)
            LocalDate startDate,
            @RequestParam(value = "endDate", required = false) LocalDate endDate
    ) {
        return ReportDTOMapper.toResponse(getReportUseCase.execute(startDate, endDate));
    }
}
