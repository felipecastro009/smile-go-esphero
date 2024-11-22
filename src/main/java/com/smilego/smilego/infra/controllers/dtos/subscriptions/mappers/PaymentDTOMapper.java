package com.smilego.smilego.infra.controllers.dtos.subscriptions.mappers;

import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.CreatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.requests.UpdatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.subscriptions.responses.PaymentResponse;

public class PaymentDTOMapper {
    public static Payment createPaymentToDomain(CreatePaymentRequest request) {
        return Payment
                .builder()
                .paymentDate(request.paymentDate())
                .amount(request.amount())
                .method(request.method())
                .status(request.status())
                .subscriptionId(request.subscriptionId())
                .build();
    }

    public static Payment updatePaymentToDomain(Long id, UpdatePaymentRequest request) {
        return Payment
                .builder()
                .id(id)
                .paymentDate(request.paymentDate())
                .amount(request.amount())
                .method(request.method())
                .status(request.status())
                .subscriptionId(request.subscriptionId())
                .build();
    }

    public static PaymentResponse toResponse(Payment payment) {
        return new PaymentResponse(
                payment.getId(),
                payment.getSubscriptionId(),
                payment.getAmount(),
                payment.getMethod(),
                payment.getStatus(),
                payment.getPaymentDate(),
                payment.getCreatedAt(),
                payment.getUpdatedAt()
        );
    }
}
