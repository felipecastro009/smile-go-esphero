package com.smilego.smilego.infra.controllers.dtos.subscriptions.requests;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record UpdateSubscriptionRequest(
        Long clientId,
        String plan,
        String status,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime startDate,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
        LocalDateTime endDate
) {}
