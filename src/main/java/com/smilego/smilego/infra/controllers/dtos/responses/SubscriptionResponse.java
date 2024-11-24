package com.smilego.smilego.infra.controllers.dtos.responses;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;

import java.time.LocalDateTime;

public record SubscriptionResponse(
        Long id,
        Long clientId,
        SubscriptionPlanEnum plan,
        SubscriptionStatusEnum status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime startDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime endDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime updatedAt
) {}
