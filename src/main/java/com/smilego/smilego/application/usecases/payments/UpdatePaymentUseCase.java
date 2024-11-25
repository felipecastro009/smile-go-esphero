package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.cache.CacheAdapter;
import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.application.repositories.SubscriptionRepository;
import com.smilego.smilego.domain.Payment;
import com.smilego.smilego.domain.Report;
import com.smilego.smilego.domain.Subscription;
import com.smilego.smilego.infra.errors.NotFoundError;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;
    private final SubscriptionRepository subscriptionRepository;
    private final CacheAdapter<Report> cacheAdapter;

    public Payment execute(Payment payment) {
        Subscription subscription = subscriptionRepository.findById(payment.getSubscriptionId());
        if (subscription == null) {
            throw new NotFoundError();
        }
        Payment result = paymentRepository.update(payment);
        paymentGateway.updateTransaction(result);
        cacheAdapter.clear("report");
        return result;
    }
}
