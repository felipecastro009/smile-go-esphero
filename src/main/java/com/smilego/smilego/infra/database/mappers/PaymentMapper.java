package com.smilego.smilego.infra.database.mappers;

import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.infra.database.entities.PaymentEntity;

public class PaymentMapper {
    public static Payment toDomain(PaymentEntity paymentEntity) {
        return Payment
                .builder()
                .id(paymentEntity.getId())
                .paymentDate(paymentEntity.getPaymentDate())
                .amount(paymentEntity.getAmount())
                .method(paymentEntity.getMethod())
                .status(paymentEntity.getStatus())
                .subscriptionId(paymentEntity.getSubscriptionId())
                .createdAt(paymentEntity.getCreatedAt())
                .updatedAt(paymentEntity.getUpdatedAt())
                .build();
    }

    public static PaymentEntity toEntity(Payment subscription) {
        return PaymentEntity
                .builder()
                .id(subscription.getId())
                .amount(subscription.getAmount())
                .method(subscription.getMethod())
                .status(subscription.getStatus())
                .paymentDate(subscription.getPaymentDate())
                .subscriptionId(subscription.getSubscriptionId())
                .createdAt(subscription.getCreatedAt())
                .updatedAt(subscription.getUpdatedAt())
                .build();
    }
}
