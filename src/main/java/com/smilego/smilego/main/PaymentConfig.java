package com.smilego.smilego.main;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.usecases.payments.*;
import com.smilego.smilego.infra.database.persistence.PaymentPersistence;
import com.smilego.smilego.infra.database.repositories.PaymentRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentRepository paymentRepository(PaymentPersistence paymentPersistence) {
        return new PaymentRepositoryImpl(paymentPersistence);
    }

    @Bean
    public FindAllPaymentUseCase findAllPaymentUseCase(PaymentRepository paymentRepository) {
        return new FindAllPaymentUseCase(paymentRepository);
    }

    @Bean
    public CreatePaymentUseCase createPaymentUseCase(PaymentRepository paymentRepository) {
        return new CreatePaymentUseCase(paymentRepository);
    }

    @Bean
    public FindAllPaymentBySubscriptionIdUseCase findAllPaymentBySubscriptionIdUseCase(PaymentRepository paymentRepository) {
        return new FindAllPaymentBySubscriptionIdUseCase(paymentRepository);
    }

    @Bean
    public UpdatePaymentUseCase updatePaymentUseCase(PaymentRepository paymentRepository) {
        return new UpdatePaymentUseCase(paymentRepository);
    }

    @Bean
    public DeletePaymentUseCase deletePaymentUseCase(PaymentRepository paymentRepository) {
        return new DeletePaymentUseCase(paymentRepository);
    }
}
