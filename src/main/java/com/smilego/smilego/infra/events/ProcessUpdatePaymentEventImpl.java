package com.smilego.smilego.infra.events;

import com.smilego.smilego.application.events.ProcessUpdatePaymentEvent;
import com.smilego.smilego.application.usecases.payments.UpdatePaymentUseCase;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.main.RabbitMQConfig;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
@AllArgsConstructor
public class ProcessUpdatePaymentEventImpl implements ProcessUpdatePaymentEvent {
    private final UpdatePaymentUseCase updatePaymentUseCase;
    @Override
    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void process(Payment payment) {
        updatePaymentUseCase.execute(payment);
        log.info("Payment message processed: {}", payment);
    }
}
