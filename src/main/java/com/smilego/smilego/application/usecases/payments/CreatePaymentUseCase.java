package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.gateways.PaymentGateway;
import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePaymentUseCase {
    private final PaymentRepository paymentRepository;
    private final PaymentGateway paymentGateway;

    public Payment execute(Payment payment) {
        Payment result = paymentRepository.create(payment);
        paymentGateway.createTransaction(result);
        return result;
    }
}
