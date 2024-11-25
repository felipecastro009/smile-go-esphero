package com.smilego.smilego.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
public class Subscription {
    private Long id;
    private Long clientId;
    private SubscriptionPlanEnum plan;
    private SubscriptionStatusEnum status;
    private List<Payment> payments;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @JsonCreator
    public Subscription(
            @JsonProperty("id") Long id,
            @JsonProperty("clientId") Long clientId,
            @JsonProperty("plan") SubscriptionPlanEnum plan,
            @JsonProperty("status") SubscriptionStatusEnum status,
            @JsonProperty("payments") List<Payment> payments,
            @JsonProperty("startDate") LocalDateTime startDate,
            @JsonProperty("endDate") LocalDateTime endDate,
            @JsonProperty("createdAt") LocalDateTime createdAt,
            @JsonProperty("updatedAt") LocalDateTime updatedAt) {
        this.id = id;
        this.clientId = clientId;
        this.plan = plan;
        this.status = status;
        this.payments = payments;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
