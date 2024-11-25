package com.smilego.smilego.unit.infra.events;

import com.smilego.smilego.application.usecases.payments.UpdatePaymentUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.events.ProcessUpdatePaymentEventImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProcessUpdatePaymentEventImplTest {
    @Mock
    private UpdatePaymentUseCase updatePaymentUseCase;

    @InjectMocks
    private ProcessUpdatePaymentEventImpl processUpdatePaymentEventImpl;


    @Test
    void shouldProcessPaymentSuccessfully() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        processUpdatePaymentEventImpl.process(payment);
        verify(updatePaymentUseCase, times(1)).execute(payment);
    }

    @Test
    void shouldLogErrorWhenExceptionOccurs() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        doThrow(new RuntimeException("Test exception")).when(updatePaymentUseCase).execute(payment);
        processUpdatePaymentEventImpl.process(payment);
        verify(updatePaymentUseCase, times(1)).execute(payment);
    }
}
