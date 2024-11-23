package com.smilego.smilego.infra.events;

import com.smilego.smilego.application.events.SendUpdatePaymentEvent;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.main.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@AllArgsConstructor
public class SendUpdatePaymentEventImpl implements SendUpdatePaymentEvent {
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void send(Payment payment) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.EXCHANGE_NAME, RabbitMQConfig.QUEUE_NAME, payment);
        log.info("Payment message send: {}", payment);
    }
}
