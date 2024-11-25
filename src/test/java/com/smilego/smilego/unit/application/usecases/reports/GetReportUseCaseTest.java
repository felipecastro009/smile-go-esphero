package com.smilego.smilego.unit.application.usecases.reports;

import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.reports.GetReportUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GetReportUseCaseTest {
    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private GetReportUseCase getReportUseCase;

    @Test
    void testExecuteWithoutDateRange() {
        Subscription activeSubscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        activeSubscription.setPayments(List.of(
                new Payment(1L, 1L, BigDecimal.valueOf(100), PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now()),
                new Payment(1L, 1L, BigDecimal.valueOf(100), PaymentStatusEnum.REFUNDED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now())
        ));
        Subscription canceledSubscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.INACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE))
                .thenReturn(List.of(activeSubscription));
        when(subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.INACTIVE))
                .thenReturn(List.of(canceledSubscription));
        Report report = getReportUseCase.execute(null, null);
        assertEquals(1, report.getActiveSubscriptions());
        assertEquals(1, report.getInactiveSubscriptions());
        assertEquals(BigDecimal.valueOf(100), report.getTotalAmount());
    }

    @Test
    void testExecuteWithDateRange() {
        Subscription activeSubscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        activeSubscription.setPayments(List.of(
                new Payment(1L, 1L, BigDecimal.valueOf(200), PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now())
        ));

        when(subscriptionRepository.findAllByStatusWithPaymentsBetweenDate(
                any(LocalDateTime.class), any(LocalDateTime.class), eq(SubscriptionStatusEnum.ACTIVE)))
                .thenReturn(List.of(activeSubscription));
        when(subscriptionRepository.findAllByStatusWithPaymentsBetweenDate(
                any(LocalDateTime.class), any(LocalDateTime.class), eq(SubscriptionStatusEnum.INACTIVE)))
                .thenReturn(List.of());
        LocalDate startDate = LocalDate.of(2024, 1, 1);
        LocalDate endDate = LocalDate.of(2024, 1, 31);
        Report report = getReportUseCase.execute(startDate, endDate);
        assertEquals(1, report.getActiveSubscriptions());
        assertEquals(0, report.getInactiveSubscriptions());
        assertEquals(BigDecimal.valueOf(200), report.getTotalAmount());
    }

    @Test
    void testExecuteWithNoApprovedPayments() {
        Subscription activeSubscription = new Subscription(
                1L,
                1L,
                SubscriptionPlanEnum.BASIC,
                SubscriptionStatusEnum.ACTIVE,
                new ArrayList<>(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        activeSubscription.setPayments(List.of(
                new Payment(1L, 1L, BigDecimal.valueOf(100), PaymentStatusEnum.REJECTED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now())
        ));

        when(subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.ACTIVE))
                .thenReturn(List.of(activeSubscription));
        when(subscriptionRepository.findAllByStatusWithPayments(SubscriptionStatusEnum.INACTIVE))
                .thenReturn(List.of());
        Report report = getReportUseCase.execute(null, null);
        assertEquals(1, report.getActiveSubscriptions());
        assertEquals(0, report.getInactiveSubscriptions());
        assertEquals(BigDecimal.ZERO, report.getTotalAmount());
    }

}
