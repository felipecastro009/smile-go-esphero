package com.smilego.smilego.application.usecases.payments;

import com.smilego.smilego.application.repositories.PaymentRepository;
import com.smilego.smilego.domain.Payment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UpdatePaymentUseCase {
    private final PaymentRepository paymentRepository;

    public Payment execute(Payment payment) {
        return paymentRepository.update(payment);
    }
}
