package com.smilego.smilego.unit.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.FindAllPaymentUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import com.smilego.smilego.infra.controllers.payments.FindAllPaymentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FindAllPaymentControllerTest {
    @Mock
    private FindAllPaymentUseCase findAllPaymentUseCase;

    @InjectMocks
    private FindAllPaymentController findAllPaymentController;

    @Test
    void shouldReturnListOfSubscriptions() {
        Payment payment1 = new Payment(1L, 1L, BigDecimal.valueOf(100.00), PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        Payment payment2 = new Payment(2L, 1L, BigDecimal.valueOf(100.00), PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        List<Payment> mockPayments = List.of(payment1, payment2);
        when(findAllPaymentUseCase.execute()).thenReturn(mockPayments);
        List<PaymentResponse> expectedResponse = mockPayments.stream()
                .map(PaymentDTOMapper::toResponse)
                .toList();

        List<PaymentResponse> actualResponse = findAllPaymentController.handle();
        assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void shouldReturnEmptyListWhenNoSubscriptionsExist() {
        when(findAllPaymentUseCase.execute()).thenReturn(List.of());
        List<PaymentResponse> actualResponse = findAllPaymentController.handle();
        assertEquals(0, actualResponse.size());
    }
}
