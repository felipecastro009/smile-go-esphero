package com.smilego.smilego.infra.controllers.dtos.mappers;

import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.controllers.dtos.requests.CreateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdateSubscriptionRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.SubscriptionResponse;

public class SubscriptionDTOMapper {
    public static Subscription createSubscriptionToDomain(CreateSubscriptionRequest request) {
        return Subscription
                .builder()
                .clientId(request.clientId())
                .plan(request.plan())
                .endDate(request.endDate())
                .startDate(request.startDate())
                .status(request.status())
                .build();
    }

    public static Subscription updateSubscriptionToDomain(Long id, UpdateSubscriptionRequest request) {
        return Subscription
                .builder()
                .id(id)
                .clientId(request.clientId())
                .plan(request.plan())
                .endDate(request.endDate())
                .startDate(request.startDate())
                .status(request.status())
                .build();
    }

    public static SubscriptionResponse toResponse(Subscription subscription) {
        return new SubscriptionResponse(
                subscription.getId(),
                subscription.getClientId(),
                subscription.getPlan(),
                subscription.getStatus(),
                subscription.getStartDate(),
                subscription.getEndDate(),
                subscription.getCreatedAt(),
                subscription.getUpdatedAt()
        );
    }
}
