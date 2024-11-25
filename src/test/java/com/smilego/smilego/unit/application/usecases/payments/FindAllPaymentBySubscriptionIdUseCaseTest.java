package com.smilego.smilego.unit.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.usecases.payments.FindAllPaymentBySubscriptionIdUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FindAllPaymentBySubscriptionIdUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase;

    @Test
    void testExecute() {
        Long subscriptionId = 1L;
        List<Payment> payments = List.of(
                new Payment(
                        1L,
                        subscriptionId,
                        BigDecimal.valueOf(100),
                        PaymentStatusEnum.APPROVED,
                        PaymentMethodEnum.CREDIT_CARD,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Payment(
                        2L,
                        subscriptionId,
                        BigDecimal.valueOf(200),
                        PaymentStatusEnum.APPROVED,
                        PaymentMethodEnum.DEBIT_CARD,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
        when(paymentRepository.findBySubscriptionId(subscriptionId)).thenReturn(payments);
        List<Payment> result = findAllPaymentBySubscriptionIdUseCase.execute(subscriptionId);
        assertEquals(payments, result);
        verify(paymentRepository, times(1)).findBySubscriptionId(subscriptionId);
    }
}
