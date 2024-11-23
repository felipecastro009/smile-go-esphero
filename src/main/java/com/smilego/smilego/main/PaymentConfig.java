package com.smilego.smilego.main;

import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.usecases.payments.*;
import com.smilego.smilego.infra.database.persistence.PaymentPersistence;
import com.smilego.smilego.infra.database.repositories.PaymentRepositoryImpl;
import com.smilego.smilego.infra.gateways.payment.PaymentGatewayImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentRepository paymentRepository(PaymentPersistence paymentPersistence) {
        return new PaymentRepositoryImpl(paymentPersistence);
    }

    @Bean
    public PaymentGateway paymentGateway(WebClient webClient) {
        return new PaymentGatewayImpl(webClient);
    }

    @Bean
    public FindAllPaymentUseCase findAllPaymentUseCase(PaymentRepository paymentRepository) {
        return new FindAllPaymentUseCase(paymentRepository);
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepository paymentRepository, PaymentGateway paymentGateway) {
        return new CreatePaymentUseCase(paymentRepository, paymentGateway);
    }

    @Bean
    public FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase(PaymentRepository paymentRepository) {
        return new FindAllPaymentBySubscriptionIdUseCase(paymentRepository);
    }

    @Bean
    public UpdatePaymentUseCase updatePaymentUseCase(PaymentRepository paymentRepository, PaymentGateway paymentGateway) {
        return new UpdatePaymentUseCase(paymentRepository, paymentGateway);
    }

    @Bean
    public DeletePaymentUseCase deletePaymentUseCase(PaymentRepository paymentRepository) {
        return new DeletePaymentUseCase(paymentRepository);
    }
}
