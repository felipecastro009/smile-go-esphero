package com.smilego.smilego.unit.infra.controllers.payments;

import com.smilego.smilego.application.usecases.payments.CreatePaymentUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.CreatePaymentRequest;
import com.smilego.smilego.infra.controllers.dtos.responses.PaymentResponse;
import com.smilego.smilego.infra.controllers.payments.CreatePaymentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreatePaymentControllerTest {
    @Mock
    private CreatePaymentUseCase createPaymentUseCase;

    @InjectMocks
    private CreatePaymentController createPaymentController;

    @Test
    void shouldCreatePaymentSuccessfully() {
        CreatePaymentRequest request = new CreatePaymentRequest(1L, BigDecimal.valueOf(100.00), PaymentMethodEnum.CREDIT_CARD, PaymentStatusEnum.APPROVED, LocalDateTime.now());
        Payment payment = new Payment(1L, 1L, BigDecimal.valueOf(100.00), PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        PaymentResponse expectedResponse = PaymentDTOMapper.toResponse(payment);
        when(createPaymentUseCase.execute(any(Payment.class))).thenReturn(payment);
        PaymentResponse actualResponse = createPaymentController.handle(request);
        assertEquals(expectedResponse.id(), actualResponse.id());
        verify(createPaymentUseCase, times(1)).execute(any(Payment.class));
    }
}
