package com.smilego.smilego.infra.controllers.dtos.responses;

import java.math.BigDecimal;

public record ReportResponse(
    Integer activeSubscriptions,
    Integer inactiveSubscriptions,
    BigDecimal totalAmount
){}
