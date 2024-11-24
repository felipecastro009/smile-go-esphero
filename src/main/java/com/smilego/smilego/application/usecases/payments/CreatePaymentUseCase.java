package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.errors.NotFoundError;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;
    private final SubscriptionRepository subscriptionRepository;

    public Payment execute(Payment payment) {
        Subscription subscription = subscriptionRepository.findById(payment.getSubscriptionId());
        if (subscription == null) {
            throw new NotFoundError();
        }
        Payment result = paymentRepository.create(payment);
        paymentGateway.createTransaction(result);
        return result;
    }
}
