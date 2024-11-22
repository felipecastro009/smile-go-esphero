package com.smilego.smilego.infra.database.mappers;

import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.database.entities.SubscriptionEntity;

public class SubscriptionMapper {
    public static Subscription toDomain(SubscriptionEntity subscriptionEntity) {
         return Subscription
                 .builder()
                 .id(subscriptionEntity.getId())
                 .clientId(subscriptionEntity.getClientId())
                 .plan(subscriptionEntity.getPlan())
                 .status(subscriptionEntity.getStatus())
                 .startDate(subscriptionEntity.getStartDate())
                 .endDate(subscriptionEntity.getEndDate())
                 .createdAt(subscriptionEntity.getCreatedAt())
                 .updatedAt(subscriptionEntity.getUpdatedAt())
                 .build();
    }

    public static SubscriptionEntity toEntity(Subscription subscription) {
        return SubscriptionEntity
                .builder()
                .id(subscription.getId())
                .clientId(subscription.getClientId())
                .plan(subscription.getPlan())
                .status(subscription.getStatus())
                .startDate(subscription.getStartDate())
                .endDate(subscription.getEndDate())
                .createdAt(subscription.getCreatedAt())
                .updatedAt(subscription.getUpdatedAt())
                .build();
    }
}
