package com.smilego.smilego.application.usecases.reports;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
public class GetReportUseCase {
    private final SubscriptionRepository subscriptionRepository;

    public Report execute() {
        List<Subscription>  subscriptionsActive = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);
        List<Subscription>  subscriptionsCanceled = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.INACTIVE);
        BigDecimal amount = subscriptionsActive.stream()
                .flatMap(subscription -> subscription.getPayments().stream())
                .filter(payment -> payment.getStatus().equals(PaymentStatusEnum.APPROVED))
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Report(subscriptionsActive.size(), subscriptionsCanceled.size(), amount);
    }
}
