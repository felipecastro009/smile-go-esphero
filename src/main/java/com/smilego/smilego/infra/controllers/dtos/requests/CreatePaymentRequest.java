package com.smilego.smilego.infra.controllers.dtos.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CreatePaymentRequest(
    Long subscriptionId,
    BigDecimal amount,
    PaymentMethodEnum method,
    PaymentStatusEnum status,
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    LocalDateTime paymentDate
) {}
