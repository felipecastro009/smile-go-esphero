package com.smilego.smilego.application.usecases.reports;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

@AllArgsConstructor
public class GetReportUseCase {
    private final SubscriptionRepository subscriptionRepository;
    private final CacheAdapter<Report> cacheAdapter;

    public Report execute(LocalDate startDate, LocalDate endDate) {
        List<Subscription>  subscriptionsActive;
        List<Subscription>  subscriptionsCanceled;

        Report report = cacheAdapter.get("report", "report:" + startDate + ":" + endDate);

        if (report != null) {
            return report;
        }

        if (startDate == null && endDate == null) {
            subscriptionsActive = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE);
            subscriptionsCanceled = subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.INACTIVE);
            Report newReport = getReport(subscriptionsActive, subscriptionsCanceled);
            cacheAdapter.put("report", "report:" + startDate + ":" + endDate, newReport);
            return newReport;
        }

        if (startDate != null && endDate != null) {
            LocalDateTime startOfDay = startDate.atStartOfDay();
            LocalDateTime endOfDay = endDate.atTime(23, 59, 59);
            subscriptionsActive = subscriptionRepository.findAllByStatusWithPaymentsBetweenDate(startOfDay, endOfDay, SubscriptionStatusEnum.ACTIVE);
            subscriptionsCanceled = subscriptionRepository.findAllByStatusWithPaymentsBetweenDate(startOfDay, endOfDay, SubscriptionStatusEnum.INACTIVE);
            Report newReport = getReport(subscriptionsActive, subscriptionsCanceled);
            cacheAdapter.put("report", "report:" + startDate + ":" + endDate, newReport);
            return newReport;
        }

        return null;
    }

    private Report getReport(List<Subscription> subscriptionsActive, List<Subscription> subscriptionsCanceled) {
        BigDecimal amount = subscriptionsActive.stream()
                .flatMap(subscription ->
                        subscription.getPayments() == null ?
                                Stream.empty() :
                                subscription.getPayments().stream())
                .filter(payment -> payment.getStatus().equals(PaymentStatusEnum.APPROVED))
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        return new Report(subscriptionsActive.size(), subscriptionsCanceled.size(), amount);
    }
}
