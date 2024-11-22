package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CreatePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public Payment execute(Payment payment) {
        return paymentRepository.create(payment);
    }
}
