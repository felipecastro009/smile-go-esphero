package com.smilego.smilego.unit.infra.controllers.payments;

import com.smilego.smilego.application.events.SendUpdatePaymentEvent;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.controllers.dtos.mappers.PaymentDTOMapper;
import com.smilego.smilego.infra.controllers.dtos.requests.UpdatePaymentRequest;
import com.smilego.smilego.infra.controllers.payments.UpdatePaymentController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UpdatePaymentControllerTest {

    @Mock
    private SendUpdatePaymentEvent sendUpdatePaymentEvent;

    @InjectMocks
    private UpdatePaymentController updatePaymentController;

    @Test
    void shouldSendUpdatePaymentEventSuccessfully() {
        Long paymentId = 1L;
        UpdatePaymentRequest request = new UpdatePaymentRequest(1L, BigDecimal.ZERO, PaymentMethodEnum.CREDIT_CARD, PaymentStatusEnum.APPROVED, LocalDateTime.now());
        Payment expectedPayment = PaymentDTOMapper.updatePaymentToDomain(paymentId, request);
        updatePaymentController.handle(paymentId, request);
        verify(sendUpdatePaymentEvent, times(1)).send(any(Payment.class));
        assertEquals(expectedPayment.getSubscriptionId(), request.subscriptionId());
    }
}
