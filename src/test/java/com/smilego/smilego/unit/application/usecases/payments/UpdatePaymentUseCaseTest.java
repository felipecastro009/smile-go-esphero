package com.smilego.smilego.unit.application.usecases.payments;

import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.application.usecases.payments.UpdatePaymentUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.domain.enums.SubscriptionPlanEnum;
import com.smilego.smilego.domain.enums.SubscriptionStatusEnum;
import com.smilego.smilego.infra.errors.NotFoundError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UpdatePaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentGateway paymentGateway;

    @Mock
    private SubscriptionRepository subscriptionRepository;

    @InjectMocks
    private UpdatePaymentUseCase updatePaymentUseCase;

    @Test
    void testExecuteSuccess() {
        Long subscriptionId = 1L;
        Payment payment = new Payment(
                1L,
                subscriptionId,
                BigDecimal.valueOf(150),
                PaymentStatusEnum.APPROVED,
                PaymentMethodEnum.CREDIT_CARD,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        Subscription subscription = new Subscription(
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
        when(subscriptionRepository.findById(subscriptionId)).thenReturn(subscription);
        when(paymentRepository.update(payment)).thenReturn(payment);
        Payment result = updatePaymentUseCase.execute(payment);
        assertEquals(payment, result);
        verify(subscriptionRepository, times(1)).findById(subscriptionId);
        verify(paymentRepository, times(1)).update(payment);
        verify(paymentGateway, times(1)).updateTransaction(payment);
    }

    @Test
    void testExecuteSubscriptionNotFound() {
        Long subscriptionId = 1L;
        Payment payment = new Payment(
                1L,
                subscriptionId,
                BigDecimal.valueOf(150),
                PaymentStatusEnum.APPROVED,
                PaymentMethodEnum.CREDIT_CARD,
                LocalDateTime.now(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
        when(subscriptionRepository.findById(subscriptionId)).thenReturn(null);
        assertThrows(NotFoundError.class, () -> updatePaymentUseCase.execute(payment));
        verify(subscriptionRepository, times(1)).findById(subscriptionId);
        verifyNoInteractions(paymentRepository);
        verifyNoInteractions(paymentGateway);
    }
}
