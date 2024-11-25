package com.smilego.smilego.unit.infra.events;

import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.enums.PaymentMethodEnum;
import com.smilego.smilego.domain.enums.PaymentStatusEnum;
import com.smilego.smilego.infra.events.SendUpdatePaymentEventImpl;
import com.smilego.smilego.main.RabbitMQConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class SendUpdatePaymentEventImplTest {
    @Mock
    private RabbitTemplate rabbitTemplate;
    @InjectMocks
    private SendUpdatePaymentEventImpl sendUpdatePaymentEventImpl;


    @Test
    void shouldSendPaymentMessageSuccessfully() {
        Payment payment = new Payment(1L, 1L, BigDecimal.ZERO, PaymentStatusEnum.APPROVED, PaymentMethodEnum.CREDIT_CARD, LocalDateTime.now(), LocalDateTime.now(), LocalDateTime.now());
        sendUpdatePaymentEventImpl.send(payment);
        verify(rabbitTemplate, times(1)).convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.QUEUE_NAME, payment);
    }
}
