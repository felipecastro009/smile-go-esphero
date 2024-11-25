package com.smilego.smilego.unit.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.usecases.payments.FindAllPaymentUseCase;
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
class FindAllPaymentUseCaseTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private FindAllPaymentUseCase findAllPaymentUseCase;

    @Test
    void testExecute() {
        List<Payment> payments = List.of(
                new Payment(
                        1L,
                        1L,
                        BigDecimal.valueOf(100),
                        PaymentStatusEnum.APPROVED,
                        PaymentMethodEnum.CREDIT_CARD,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                ),
                new Payment(
                        2L,
                        2L,
                        BigDecimal.valueOf(200),
                        PaymentStatusEnum.PENDING,
                        PaymentMethodEnum.DEBIT_CARD,
                        LocalDateTime.now(),
                        LocalDateTime.now(),
                        LocalDateTime.now()
                )
        );
        when(paymentRepository.find()).thenReturn(payments);
        List<Payment> result = findAllPaymentUseCase.execute();
        assertEquals(payments, result);
        verify(paymentRepository, times(1)).find();
    }
}
