package com.smilego.smilego.infra.controllers.dtos.subscriptions.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePaymentRequest(
    Long subscriptionId,
    BigDecimal amount,
    String method,
    String status,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime paymentDate
) {}
